package com.realworld.feature.message.service.chat;

import com.realworld.feature.member.repository.MemberRepository;
import com.realworld.feature.member.service.MemberQueryService;
import com.realworld.feature.message.controller.request.CreateChatRoomRequest;
import com.realworld.feature.message.controller.resonse.CreateChatRoomResponse;
import com.realworld.feature.message.domain.chat.CreateChatRoom;
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
import static com.realworld.feature.message.domain.chat.CreateChatRoom.initialCreateChatRoom;
import static com.realworld.feature.message.domain.chat.FindChatRoom.toCreateModel;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomCommandCommandServiceImpl implements ChatRoomCommandService {

    private final MemberQueryService memberQueryService;
    private final ChatRoomRepository chatRoomRepository;
    private final ProductRepository productRepository;
    private final ChatRoomQueryService chatRoomQueryService;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public  CreateChatRoomResponse createChatRoomForPersonal(String userId, CreateChatRoomRequest request) {

        // 요청한 유저가 방 생성자인지 확인
        if(!userId.equals(request.getRoomMakerId()))
            throw new CustomChatExceptionHandler(ErrorCode.CHAT_USER_NOT_FOUND);

        // 채팅방이 이미 존재하는지 확인
        Optional<ChatRoomDetailDto> chatRoom = chatRoomQueryService.findByChatRoom(toCreateModel(request));


        if(chatRoom.isPresent()){
            return convertToResponse(chatRoom.get(), true);
        }

        // 제품 실제로 존재하는지 확인
        ProductJpaEntity entity = productRepository.findById(request.getProductSeq()).orElseThrow(() -> new CustomProductExceptionHandler(ErrorCode.NOT_EXISTS_PRODUCT));

        // 제품을 생성한 사용자와 RoomMaker와 동일하면 에러
        if(entity.getMember().getUserId().equals(request.getRoomMakerId())) throw new CustomChatExceptionHandler(ErrorCode.CHAT_DUPLICATE_USER);

        // 채팅방 저장
        CreateChatRoom room = initialCreateChatRoom(request.getRoomMakerId(), request.getGuestId());

        room.setProduct(entity);
        ChatRoomEntity roomSave = chatRoomRepository.save(room.toEntity());

        // 채팅방 응답 반환
        return convertToResponse(roomSave, false);
    }


}
