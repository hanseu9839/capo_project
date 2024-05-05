package com.realworld.feature.product.controller.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductGenerationRequest {
    @NotNull(message = "제목이 입력되지 않았습니다.")
    private String title;
    
    @NotNull(message = "내용이 입력되지 않았습니다.")
    private String content;

    @NotNull(message = "카테고리를 입력해주세요.")
    private String category;

    @NotNull(message = "가격을 입력해주세요.")
    private Long price;

    @JsonProperty("image_seq")
    private List<String> imageSeq;
}
