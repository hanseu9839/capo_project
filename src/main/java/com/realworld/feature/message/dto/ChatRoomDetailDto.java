package com.realworld.feature.message.dto;


import lombok.*;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDetailDto {

    private UUID roomId;

    private Long price;

    private String title;

    private String thumbnailUrl;

    private Long productSeq;
}
