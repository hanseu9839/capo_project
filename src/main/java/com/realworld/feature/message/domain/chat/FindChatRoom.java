package com.realworld.feature.message.domain.chat;

import com.realworld.feature.message.controller.request.CreateChatRoomRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindChatRoom {

    private String roomMaker;

    private String guest;

    private Long productSeq;

    public static FindChatRoom toCreateModel(CreateChatRoomRequest request){
        return FindChatRoom.builder()
                .roomMaker(request.getRoomMakerId())
                .guest(request.getGuestId())
                .productSeq(request.getProductSeq())
                .build();
    }
}
