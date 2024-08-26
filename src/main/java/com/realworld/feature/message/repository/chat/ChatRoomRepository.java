package com.realworld.feature.message.repository.chat;

import com.realworld.feature.message.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, UUID>, CustomChatRepository {
    List<ChatRoomEntity> findDistinctByRoomMakerOrGuest(String roomMaker, String guest);

}
