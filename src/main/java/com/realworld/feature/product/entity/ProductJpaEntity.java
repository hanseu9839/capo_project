package com.realworld.feature.product.entity;

import com.realworld.feature.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
    /**
     * TODO: ProductJpaEntity Image, Like 좋아요 기능 컬럼 미구현 (추후 개발)
     */
    @Table(name="product")
    @ToString
    @Entity
    @NoArgsConstructor
    @Getter @Setter
    @EntityListeners(AuditingEntityListener.class)
    public class ProductJpaEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long productSeq;
        private String title;
        @Column(length = 50000)
        private String content;
        private Long price;
        private String category;
        private int views;
        private LocalDateTime createDt;
        private LocalDateTime regDt;

        @Builder
        public ProductJpaEntity(Long productSeq, String title, String content, Long price, String category, int views, LocalDateTime createDt, LocalDateTime regDt){
            this.productSeq = productSeq;
            this.title = title;
            this.content = content;
            this.price = price;
            this.category = category;
            this.views = views;
            this.createDt = createDt;
            this.regDt = regDt;
        }

        public Product toDomain() {
            return Product.builder()
                    .productSeq(getProductSeq())
                    .title(getTitle())
                    .price(getPrice())
                    .category(getCategory())
                    .views(getViews())
                    .createDt(getCreateDt())
                    .regDt(getRegDt())
                    .build();
        }
    }

