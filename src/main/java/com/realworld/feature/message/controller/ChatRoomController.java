package com.realworld.feature.message.controller;

import com.realworld.feature.message.controller.request.CreateChatRoomRequest;
import com.realworld.feature.message.controller.resonse.CreateChatRoomResponse;
import com.realworld.feature.message.service.chat.ChatRoomCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ChatRoomController {

    private final ChatRoomCommandService chatRoomCommandService;


    @PostMapping("/personal")
    public CreateChatRoomResponse createChatRoomForPersonal(@AuthenticationPrincipal User user, @RequestBody CreateChatRoomRequest request) {
        chatRoomCommandService.createChatRoomForPersonal(user.getUsername(), request);

        return null;
    }



}
