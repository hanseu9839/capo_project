package com.realworld.feature.message.controller.resonse;

import com.realworld.feature.message.dto.ChatRoomListDto;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class FindChatRoomResponse {
    List<ChatRoomListDto> list;
}
