package com.realworld.feature.product.domain;


import com.realworld.feature.product.entity.ProductFileJpaEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
public class ProductFile {

    private Product product;

    private String userId;

    private UUID id;

    private LocalDateTime createAt;

    private LocalDateTime modifiedAt;

    public ProductFileJpaEntity toEntity() {
        return ProductFileJpaEntity.builder()
                .userId(this.userId)
                .product(this.product.toEntity())
                .id(this.id)
                .createAt(this.createAt)
                .modifiedAt(this.modifiedAt)
                .build();
    }
}
