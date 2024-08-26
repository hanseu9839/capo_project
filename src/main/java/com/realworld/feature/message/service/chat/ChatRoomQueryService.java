package com.realworld.feature.message.service.chat;

import com.realworld.feature.message.domain.FindChatRoom;
import com.realworld.feature.message.dto.ChatRoomDetailDto;
import com.realworld.feature.message.entity.ChatRoomEntity;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ChatRoomQueryService {

    Optional<ChatRoomDetailDto> findByChatRoom(FindChatRoom findChatRoom);

    List<ChatRoomEntity> findByChatRoomList(String userId);
}
