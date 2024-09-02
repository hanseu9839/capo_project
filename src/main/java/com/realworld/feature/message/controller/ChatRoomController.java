package com.realworld.feature.message.controller;

import com.realworld.feature.message.controller.request.CreateChatRoomRequest;
import com.realworld.feature.message.controller.resonse.CreateChatRoomResponse;
import com.realworld.feature.message.controller.resonse.FindChatRoomResponse;
import com.realworld.feature.message.dto.ChatRoomListDto;
import com.realworld.feature.message.service.chat.ChatRoomCommandService;
import com.realworld.feature.message.service.chat.ChatRoomQueryService;
import com.realworld.global.code.SuccessCode;
import com.realworld.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
public class ChatRoomController {

    private final ChatRoomCommandService chatRoomCommandService;
    private final ChatRoomQueryService chatRoomQueryService;

    @PostMapping("/personal")
    public ResponseEntity<ApiResponse<CreateChatRoomResponse>> createChatRoomForPersonal(@AuthenticationPrincipal User user, @RequestBody CreateChatRoomRequest request) {

        // 유저의 정보를 이용해 1:1 채팅방을 생성하는 서비스 호출
        CreateChatRoomResponse response =chatRoomCommandService.createChatRoomForPersonal(user.getUsername(), request);

        ApiResponse<CreateChatRoomResponse> apiResponse = null;


        // 응답 데이터와 성공 상태, 메시지를 포함한 ApiResponse 객체 생성
        if(response.isExist()) apiResponse = new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS.getStatus(), SuccessCode.SELECT_SUCCESS.getMessage());
        else apiResponse = new ApiResponse<>(response, SuccessCode.INSERT_SUCCESS.getStatus(), SuccessCode.INSERT_SUCCESS.getMessage());

        // HTTP 200 OK 응답과 함께 API 응답 데이터 반환
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<FindChatRoomResponse>> findChatRoomList(@AuthenticationPrincipal User user){

        List<ChatRoomListDto> list = chatRoomQueryService.findByChatRoomList(user.getUsername());

        FindChatRoomResponse response  = FindChatRoomResponse.builder()
                .list(list)
                .build();

        ApiResponse<FindChatRoomResponse> apiResponse = new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS.getStatus(), SuccessCode.SELECT_SUCCESS.getMessage());


        return ResponseEntity.ok(apiResponse);
    }
}
