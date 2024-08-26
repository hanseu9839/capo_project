package com.realworld.feature.message.repository.chat;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.realworld.feature.message.domain.FindChatRoom;
import com.realworld.feature.message.dto.ChatRoomDetailDto;
import com.realworld.feature.message.entity.QChatRoomEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomChatRepositoryImpl implements CustomChatRepository{

    private final JPAQueryFactory queryFactory;

    private final QChatRoomEntity qChatRoom = QChatRoomEntity.chatRoomEntity;



    @Override
    public Optional<ChatRoomDetailDto> findChatRoom(FindChatRoom findChatRoom) {

        return Optional.ofNullable(queryFactory
                .select(
                        Projections.constructor(ChatRoomDetailDto.class, qChatRoom.roomId, qChatRoom.product.price, qChatRoom.product.title, qChatRoom.product.thumbnailUrl, qChatRoom.product.productSeq)
                )
                .from(qChatRoom)
                .where(chatRoomCriteria(findChatRoom)).fetchOne());
    }


    private Predicate chatRoomCriteria(FindChatRoom findChatRoom) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qChatRoom.roomMaker.eq(findChatRoom.getRoomMaker()));
        builder.and(qChatRoom.guest.eq(findChatRoom.getGuest()));
        builder.and(qChatRoom.product.productSeq.eq(findChatRoom.getProductSeq()));
        return builder;
    }



}
