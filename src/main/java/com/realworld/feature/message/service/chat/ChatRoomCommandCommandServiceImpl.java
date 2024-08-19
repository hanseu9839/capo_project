package com.realworld.feature.message.service.chat;

import com.realworld.feature.member.domain.Member;
import com.realworld.feature.member.service.MemberQueryService;
import com.realworld.feature.message.controller.request.CreateChatRoomRequest;
import com.realworld.feature.message.domain.CreateChatRoom;
import com.realworld.feature.message.entity.ChatRoomEntity;
import com.realworld.feature.message.repository.chat.ChatRoomRepository;
import com.realworld.feature.product.domain.Product;
import com.realworld.feature.product.entity.ProductJpaEntity;
import com.realworld.feature.product.repository.ProductRepository;
import com.realworld.global.code.ErrorCode;
import com.realworld.global.config.exception.CustomChatExceptionHandler;
import com.realworld.global.config.exception.CustomProductExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.realworld.feature.message.domain.CreateChatRoom.initialCreateChatRoom;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomCommandCommandServiceImpl implements ChatRoomCommandService {

    private final MemberQueryService memberQueryService;
    private final ChatRoomRepository chatRoomRepository;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public void createChatRoomForPersonal(String userId, CreateChatRoomRequest request) {

        if(!userId.equals(request.getRoomMakerId()))
            throw new CustomChatExceptionHandler(ErrorCode.CHAT_USER_NOT_FOUND);

        // room  저장 로직
        Member roomMaker = memberQueryService.getMemberByUserId(request.getRoomMakerId());
        Member guest = memberQueryService.getMemberByUserId(request.getGuestId());


        CreateChatRoom room = initialCreateChatRoom(roomMaker, guest);
        ChatRoomEntity roomSave = chatRoomRepository.save(room.toEntity());

        ProductJpaEntity product = productRepository.findById(request.getProductSeq()).orElseThrow(()-> new CustomProductExceptionHandler(ErrorCode.NOT_EXISTS_PRODUCT));

        roomSave.setProduct(product);

    }


}
