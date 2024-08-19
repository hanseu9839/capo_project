package com.realworld.feature.message.repository.chat;

import com.realworld.feature.message.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {



}
