package com.realworld.feature.product.controller.request;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateRequest {
    @NotNull(message = "요청 값 오류입니다.")
    private Long productSeq;

    @NotNull(message = "내용이 입력 되지 않았습니다.")
    private String content;

    
}
