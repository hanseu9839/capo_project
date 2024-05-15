package com.realworld.feature.product.controller.request;


import com.realworld.global.category.GroupCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductGenerationRequest {
    @NotNull(message = "제목이 입력되지 않았습니다.")
    private String title;

    @NotNull(message = "내용이 입력되지 않았습니다.")
    private String content;

    @NotNull(message = "카테고리를 입력해주세요.")
    private GroupCategory category;

    @NotNull(message = "가격을 입력해주세요.")
    private Long price;

    
}
