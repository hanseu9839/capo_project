package com.realworld.feature.message.domain;

import com.realworld.feature.member.domain.Member;
import com.realworld.feature.message.entity.ChatRoomEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateChatRoom {

    private String roomId;

    private String lastMessage;

    private Long productSeq;

    private String roomMaker;

    private String guest;

    private String createdAt;

    public static CreateChatRoom initialCreateChatRoom(Member roomMaker, Member guest){
        return CreateChatRoom.builder()
                .roomId(UUID.randomUUID().toString())
                .roomMaker(roomMaker.getUserId())
                .guest(guest.getUserId())
                .build();
    }

    public ChatRoomEntity toEntity() {
        return ChatRoomEntity.builder()
                .roomId(UUID.fromString(this.getRoomId()))
                .roomMaker(this.getRoomMaker())
                .guest(this.getRoomMaker())
                .build();
    }
}
