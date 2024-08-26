package com.realworld.feature.message.domain;


import com.realworld.feature.message.entity.MessageEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String roomId;

    private String sender;

    private String message;


    public static Message createEnterMessage() {

//        return Message.builder()
//                .roomId(request.getRoomId())
//                .sender(request.getSender())
//                .message(request.getSender() + "님이 채팅방에 참여하였습니다.")
//                .build();
        return null;
    }

    public static Message createMessage() {

//        return Message.builder()
//                .roomId(request.getRoomId())
//                .sender(request.getSender())
//                .message(request.getSender())
//                .build();
        return null;
    }
    public MessageEntity toEntity(){
        return MessageEntity.builder()
                .roomId(this.roomId)
                .sender(this.sender)
                .message(this.message)
                .build();
    }
}
