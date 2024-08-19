package com.realworld.feature.message.controller.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateChatRoomRequest {

    private String roomMakerId;

    private String guestId;

    private Long productSeq;
}
