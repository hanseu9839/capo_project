package com.realworld.feature.product.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.realworld.feature.file.entity.ProductFileJpaEntity;
import com.realworld.feature.member.entity.MemberJpaEntity;
import com.realworld.feature.product.entity.ProductJpaEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;


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

    //    private UUID id;
    private Long productSeq;
    private String title;
    private String userId;
    private List<ProductFileJpaEntity> images;
    private MemberJpaEntity member;
    private String content;
    private String category;
    private Long price;
    private int views;
    private LocalDateTime createDt;
    private LocalDateTime regDt;

    public ProductJpaEntity toEntity() {
        return ProductJpaEntity.builder()
//                .id(this.id)
                .userId(this.userId)
                .images(this.images)
                .productSeq(this.productSeq)
                .title(this.title)
                .member(this.member)
                .content(this.content)
                .category(this.category)
                .price(this.price)
                .build();
    }

}
