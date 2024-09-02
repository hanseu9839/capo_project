package com.realworld.feature.message.service.message;

import com.realworld.feature.message.controller.request.CreateChatMessageRequest;

public interface MessageCommandService {
    void saveMessage(CreateChatMessageRequest request);
}
