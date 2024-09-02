package com.realworld.feature.message.controller.request;

import com.realworld.feature.message.entity.MessageEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateChatMessageRequest {

    private String roomId;

    private String writer;

    private String message;

    public MessageEntity toEntity(){
        return MessageEntity.builder()
                .roomId(this.roomId)
                .writer(this.writer)
                .message(this.message)
                .build();
    }
}
