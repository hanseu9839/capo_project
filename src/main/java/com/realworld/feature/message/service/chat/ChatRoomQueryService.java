package com.realworld.feature.message.service.chat;

import com.realworld.feature.message.domain.chat.FindChatRoom;
import com.realworld.feature.message.dto.ChatRoomDetailDto;
import com.realworld.feature.message.dto.ChatRoomListDto;
import com.realworld.feature.message.entity.ChatRoomEntity;

import java.util.List;
import java.util.Optional;

public interface ChatRoomQueryService {

    Optional<ChatRoomDetailDto> findByChatRoom(FindChatRoom findChatRoom);

    List<ChatRoomListDto> findByChatRoomList(String userId);
}
