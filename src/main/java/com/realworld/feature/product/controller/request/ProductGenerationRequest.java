package com.realworld.feature.product.controller.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductGenerationRequest {
    @NotNull(message = "제목이 입력되지 않았습니다.")
    private String title;
    @NotNull(message = "내용이 입력되지 않았습니다.")
    private String contents;
    @NotNull(message = "카테고리를 입력해주세요.")
    private String category;
    @NotNull(message = "가격을 입력해주세요.")
    private Long price;
}
