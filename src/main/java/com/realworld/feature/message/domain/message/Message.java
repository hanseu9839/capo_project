package com.realworld.feature.message.domain.message;


import com.realworld.feature.message.entity.MessageEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String roomId;

    private String writer;

    private String message;

    private LocalDateTime createAt;

    public MessageEntity toEntity(){
        return MessageEntity.builder()
                .roomId(this.roomId)
                .writer(this.writer)
                .message(this.message)
                .build();
    }
}
