package com.realworld.feature.product.controller.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.realworld.global.category.GroupCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductUpdateRequest {
    @NotNull(message = "요청 값 오류입니다.")
    private Long productSeq;

    @NotNull(message = "제목이 입력되지 않았습니다.")
    private String title;

    @NotNull(message = "내용이 입력되지 않았습니다.")
    private String content;

    @NotNull(message = "카테고리가 입력되지 않았습니다.")
    private GroupCategory category;

    @NotNull(message = "가격이 입력되지 않았습니다.")
    private Long price;

    @JsonProperty("thumbnail_id")
    private String thumbnailId;

    private List<String> images;
}
