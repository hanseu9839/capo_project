package com.realworld.feature.message.service.chat;

import com.realworld.feature.message.domain.FindChatRoom;
import com.realworld.feature.message.dto.ChatRoomDetailDto;
import com.realworld.feature.message.entity.ChatRoomEntity;
import com.realworld.feature.message.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomQueryServiceImpl implements ChatRoomQueryService{

    private final ChatRoomRepository repository;


    @Override
    public Optional<ChatRoomDetailDto> findByChatRoom(FindChatRoom room) {
        return repository.findChatRoom(room);
    }

    @Override
    public List<ChatRoomEntity> findByChatRoomList(String userId) {

       List<ChatRoomEntity> list =repository.findDistinctByRoomMakerOrGuest(userId, userId);

       for(ChatRoomEntity entity : list) {
           log.info("entity :: {}", entity);
       }

        return null;
    }
}
