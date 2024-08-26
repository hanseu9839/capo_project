package com.realworld.feature.message.service.chat;

import com.realworld.feature.member.domain.Member;
import com.realworld.feature.member.service.MemberQueryService;
import com.realworld.feature.message.controller.request.CreateChatRoomRequest;
import com.realworld.feature.message.controller.resonse.CreateChatRoomResponse;
import com.realworld.feature.message.domain.CreateChatRoom;
import com.realworld.feature.message.dto.ChatRoomDetailDto;
import com.realworld.feature.message.entity.ChatRoomEntity;
import com.realworld.feature.message.repository.chat.ChatRoomRepository;
import com.realworld.feature.product.entity.ProductJpaEntity;
import com.realworld.feature.product.repository.ProductRepository;
import com.realworld.global.code.ErrorCode;
import com.realworld.global.config.exception.CustomChatExceptionHandler;
import com.realworld.global.config.exception.CustomProductExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.realworld.feature.message.controller.resonse.CreateChatRoomResponse.convertToResponse;
import static com.realworld.feature.message.domain.CreateChatRoom.initialCreateChatRoom;
import static com.realworld.feature.message.domain.FindChatRoom.toCreateModel;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomCommandCommandServiceImpl implements ChatRoomCommandService {

    private final MemberQueryService memberQueryService;
    private final ChatRoomRepository chatRoomRepository;
    private final ProductRepository productRepository;
    private final ChatRoomQueryService chatRoomQueryService;


    @Transactional
    @Override
    public  CreateChatRoomResponse createChatRoomForPersonal(String userId, CreateChatRoomRequest request) {

        // 요청한 유저가 방 생성자인지 확인
        if(!userId.equals(request.getRoomMakerId()))
            throw new CustomChatExceptionHandler(ErrorCode.CHAT_USER_NOT_FOUND);

        // 채팅방이 이미 존재하는지 확인
        Optional<ChatRoomDetailDto> chatRoom = chatRoomQueryService.findByChatRoom(toCreateModel(request));
        if(chatRoom.isPresent()){
            return convertToResponse(chatRoom.get());
        }

        // 제품 실제로 존재하는지 확인
        ProductJpaEntity entity = productRepository.findById(request.getProductSeq()).orElseThrow(() -> new CustomProductExceptionHandler(ErrorCode.NOT_EXISTS_PRODUCT));

        // room  저장 로직
        Member roomMaker = memberQueryService.getMemberByUserId(request.getRoomMakerId());
        Member guest = memberQueryService.getMemberByUserId(request.getGuestId());

        // 채팅방 저장
        CreateChatRoom room = initialCreateChatRoom(roomMaker, guest);
        room.setProduct(entity);
        ChatRoomEntity roomSave = chatRoomRepository.save(room.toEntity());

        log.info("roomSave :: {}", roomSave);

        // 채팅방 응답 반환
        return convertToResponse(roomSave);
    }



}
