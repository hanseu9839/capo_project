package com.realworld.feature.message.service.chat;


import com.realworld.feature.message.controller.request.CreateChatRoomRequest;

public interface ChatRoomCommandService {

    void createChatRoomForPersonal(String userId, CreateChatRoomRequest request);
}
