package com.realworld.feature.message.dto;


import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ToString
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

    private String userId;

    private String profileImage;
}
