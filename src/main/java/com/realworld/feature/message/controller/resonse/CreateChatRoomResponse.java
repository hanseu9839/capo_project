package com.realworld.feature.message.controller.resonse;


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
public class CreateChatRoomResponse {

    private String roomId;

    private Long price;

    private String title;

    private String thumbnailUrl;

    private Long productSeq;

    public static CreateChatRoomResponse convertToResponse(ChatRoomDetailDto dto){
        return CreateChatRoomResponse.builder()
                .roomId(String.valueOf(dto.getRoomId()))
                .price(dto.getPrice())
                .title(dto.getTitle())
                .productSeq(dto.getProductSeq())
                .thumbnailUrl(dto.getThumbnailUrl())
                .build();
    }


    public static CreateChatRoomResponse convertToResponse(ChatRoomEntity entity){
        return CreateChatRoomResponse.builder()
                .roomId(String.valueOf(entity.getRoomId()))
                .price(entity.getProduct().getPrice())
                .title(entity.getProduct().getTitle())
                .productSeq(entity.getProduct().getProductSeq())
                .thumbnailUrl(entity.getProduct().getThumbnailUrl())
                .build();
    }
}
