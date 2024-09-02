package com.realworld.feature.message.dto;

import com.realworld.feature.message.entity.MessageEntity;
import lombok.*;

import java.util.UUID;

@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomListDto {

    private UUID roomId;

    private String title;

}
