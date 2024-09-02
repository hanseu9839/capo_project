package com.realworld.feature.message.service.message;


import com.realworld.feature.message.controller.request.CreateChatMessageRequest;
import com.realworld.feature.message.repository.message.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageCommandServiceImpl implements MessageCommandService {

    private final MessageRepository repository;

    @Override
    public void saveMessage(CreateChatMessageRequest request) {

        repository.save(request.toEntity());
    }
}
