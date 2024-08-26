package com.realworld.feature.message.service.chat;


import com.realworld.feature.message.controller.request.CreateChatRoomRequest;
import com.realworld.feature.message.controller.resonse.CreateChatRoomResponse;

public interface ChatRoomCommandService {

    CreateChatRoomResponse createChatRoomForPersonal(String userId, CreateChatRoomRequest request);
}
