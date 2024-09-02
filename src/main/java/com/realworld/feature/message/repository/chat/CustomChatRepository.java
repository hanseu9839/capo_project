package com.realworld.feature.message.repository.chat;

import com.realworld.feature.message.domain.chat.FindChatRoom;
import com.realworld.feature.message.dto.ChatRoomDetailDto;
import com.realworld.feature.message.dto.ChatRoomListDto;

import java.util.List;
import java.util.Optional;

public interface CustomChatRepository {
    Optional<ChatRoomDetailDto> findChatRoom(FindChatRoom findChatRoom);
    List<ChatRoomListDto> findByChatRoomList(String roomMaker, String guest);
}
