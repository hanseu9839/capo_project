package com.realworld.feature.product.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.realworld.feature.product.entity.ProductJpaEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


/**
 * TODO: Like,Image 추가해야합니다.
 */
@Builder
@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude
public class Product {
    private Long productSeq;
    private String title;
    private String content;
    private String category;
    private Long price;
    private int views;
    private LocalDateTime createDt;
    private LocalDateTime regDt;

    public ProductJpaEntity toEntity() {
        return ProductJpaEntity.builder()
                .title(this.title)
                .content(this.content)
                .category(this.category)
                .price(this.price)
                .build();
    }
}
