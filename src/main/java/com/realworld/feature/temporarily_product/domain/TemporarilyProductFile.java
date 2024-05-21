package com.realworld.feature.temporarily_product.domain;

import com.realworld.feature.temporarily_product.entity.TemporarilyProductFileJpaEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@ToString
public class TemporarilyProductFile {
    private TemporarilyProduct product;

    private String userId;

    private UUID id;

    private LocalDateTime createAt;

    private LocalDateTime modifiedAt;

    public TemporarilyProductFileJpaEntity toEntity() {
        return TemporarilyProductFileJpaEntity.builder()
                .userId(this.userId)
                .product(this.product.toEntity())
                .id(this.id)
                .createAt(this.createAt)
                .modifiedAt(this.modifiedAt)
                .build();
    }

    public TemporarilyProductFileJpaEntity searchToEntity() {
        return TemporarilyProductFileJpaEntity.builder()
                .userId(this.userId)
                .id(this.id)
                .createAt(this.createAt)
                .modifiedAt(this.modifiedAt)
                .build();
    }
}
