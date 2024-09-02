package com.realworld.feature.message.repository.chat;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.realworld.feature.member.entity.QMemberJpaEntity;
import com.realworld.feature.message.domain.chat.FindChatRoom;
import com.realworld.feature.message.dto.ChatRoomDetailDto;
import com.realworld.feature.message.dto.ChatRoomListDto;
import com.realworld.feature.message.entity.QChatRoomEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class CustomChatRepositoryImpl implements CustomChatRepository{

    private final JPAQueryFactory queryFactory;

    private final QChatRoomEntity qChatRoom = QChatRoomEntity.chatRoomEntity;
    private final QMemberJpaEntity qMemberJpa = QMemberJpaEntity.memberJpaEntity;

    @Override
    public Optional<ChatRoomDetailDto> findChatRoom(FindChatRoom findChatRoom) {

        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ChatRoomDetailDto.class, qChatRoom.roomId, qChatRoom.product.price, qChatRoom.product.title, qChatRoom.product.thumbnailUrl,  qChatRoom.product.productSeq, qMemberJpa.userId.as("userId"), qMemberJpa.profileImage.as("profileImage")))
                .from(qChatRoom)
                .join(qMemberJpa).on(qChatRoom.guest.eq(qMemberJpa.userId))
                .where(
                        qChatRoom.product.productSeq.eq(findChatRoom.getProductSeq())
                                .and(qChatRoom.guest.eq(findChatRoom.getGuest()))
                )
                .fetchOne()
        );
    }


    @Override
    public List<ChatRoomListDto> findByChatRoomList(String roomMaker, String guest) {

        return queryFactory.selectDistinct(
                        Projections.constructor(ChatRoomListDto.class, qChatRoom.roomId, qChatRoom.product.title)
                )
                .from(qChatRoom)
                .where(findChatRoomListCriteria(roomMaker, guest))
                .fetch();

    }


    private Predicate findChatRoomListCriteria(String roomMaker, String guest) {
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression roomMakerCondition = qChatRoom.roomMaker.eq(roomMaker);
        BooleanExpression guestCondition = qChatRoom.guest.eq(guest);
        builder.or(roomMakerCondition).or(guestCondition);

        return builder;
    }


}
