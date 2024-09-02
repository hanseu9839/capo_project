package com.realworld.feature.message.controller;

import com.realworld.feature.message.controller.request.CreateChatMessageRequest;
import com.realworld.feature.message.service.message.MessageCommandService;
import com.realworld.global.code.SuccessCode;
import com.realworld.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MessageController {

    private final MessageCommandService messageCommandService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping(value = "/{roomId}/messages")
    public ResponseEntity<ApiResponse<?>> enterMessage(@DestinationVariable("roomId") String roomId, @RequestBody CreateChatMessageRequest request) {
        log.info("enter :: {}", request.getRoomId());

        messageCommandService.saveMessage(request);

        messagingTemplate.convertAndSend("/sub/"+ roomId, request);

        ApiResponse<?> apiResponse = new ApiResponse<>(null, SuccessCode.INSERT_SUCCESS.getStatus(), SuccessCode.INSERT_SUCCESS.getMessage());

        return ResponseEntity.ok(apiResponse);
    }


}
