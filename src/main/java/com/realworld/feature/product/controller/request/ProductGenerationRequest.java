package com.realworld.feature.product.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.realworld.feature.member.domain.Member;
import com.realworld.feature.product.domain.ProductFile;
import com.realworld.global.category.GroupCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductGenerationRequest {
    @NotNull(message = "요청 값 오류입니다.")
    private GroupCategory category;

    @NotNull(message = "내용이 입력되지 않았습니다.")
    private String content;

    private String hashtag;

    @NotNull(message = "가격이 입력되지 않았습니다.")
    private Long price;

    @NotNull(message = "제목이 입력되지 않았습니다.")
    private String title;
    
    private Member member;

    private List<ProductFile> images;

    @JsonProperty("thumbnail_id")
    private String thumbnailId;
}
