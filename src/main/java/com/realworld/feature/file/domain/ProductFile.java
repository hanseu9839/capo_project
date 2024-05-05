package com.realworld.feature.file.domain;


import com.realworld.feature.file.entity.ProductFileJpaEntity;
import com.realworld.feature.product.domain.Product;
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

    private String name;

    private String contentType;

    private String extension;

    private long size;

    private String imageUrl;

    private LocalDateTime createAt;

    private LocalDateTime modifiedAt;

    public void updateId(UUID id) {
        this.id = id;
    }

    public void updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ProductFileJpaEntity toEntity() {
        return ProductFileJpaEntity.builder()
                .userId(this.userId)
                .product(this.product.toEntity())
                .id(this.id)
                .imageUrl(this.imageUrl)
                .createAt(this.createAt)
                .modifiedAt(this.modifiedAt)
                .build();
    }
}
