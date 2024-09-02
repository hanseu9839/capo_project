package com.realworld.feature.message.controller.resonse;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.realworld.feature.message.dto.ChatRoomDetailDto;
import com.realworld.feature.message.entity.ChatRoomEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateChatRoomResponse {

    private String roomId;

    private Long price;

    private String title;

    private String thumbnailUrl;

    private Long productSeq;

    private String userId;

    private String profileImage;

    private boolean exist;

    public static CreateChatRoomResponse convertToResponse(ChatRoomDetailDto dto, boolean exist){
        return CreateChatRoomResponse.builder()
                .roomId(String.valueOf(dto.getRoomId()))
                .price(dto.getPrice())
                .title(dto.getTitle())
                .productSeq(dto.getProductSeq())
                .thumbnailUrl(dto.getThumbnailUrl())
                .userId(dto.getUserId())
                .profileImage(dto.getProfileImage())
                .exist(exist)
                .build();
    }

    public static CreateChatRoomResponse convertToResponse(ChatRoomEntity entity, boolean exist){
        return CreateChatRoomResponse.builder()
                .roomId(String.valueOf(entity.getRoomId()))
                .price(entity.getProduct().getPrice())
                .title(entity.getProduct().getTitle())
                .productSeq(entity.getProduct().getProductSeq())
                .thumbnailUrl(entity.getProduct().getThumbnailUrl())
                .exist(exist)
                .build();
    }
}
