package com.realworld.feature.product.entity;

import com.realworld.feature.like.entity.LikeJpaEntity;
import com.realworld.feature.member.entity.MemberJpaEntity;
import com.realworld.feature.message.entity.ChatRoomEntity;
import com.realworld.feature.product.domain.Product;
import com.realworld.global.category.GroupCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO: ProductJpaEntity Image, Like 좋아요 기능 컬럼 미구현 (추후 개발)
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "product")
public class ProductJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_seq")
    private Long productSeq;

    @Column(name = "user_id")
    private String userId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private MemberJpaEntity member;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<ProductFileJpaEntity> images = new ArrayList<>();

    private String title;

    @ColumnDefault("0")
    @Column(name = "like_count")
    private int likeCount;

    @Column(length = 50000)
    private String content;

    private Long price;

    @Enumerated(EnumType.STRING)
    private GroupCategory category;

    private String hashtag;

    @ColumnDefault("0")
    private int views;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @CreatedDate
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LikeJpaEntity> likes;


    public Product toDomain() {
        return Product.builder()
                .productSeq(this.productSeq)
                .title(this.title)
                .likeCount(this.likeCount)
                .userId(this.userId)
                .member(this.member.toDomain())
                .content(this.content)
                .price(this.price)
                .category(this.category)
                .views(this.views)
                .thumbnailUrl(this.thumbnailUrl)
                .createdAt(this.createAt)
                .modifiedAt(this.modifiedAt)
                .build();
    }

    public Product generationToDomain() {
        return Product.builder()
                .productSeq(this.productSeq)
                .images(this.images.stream().map(ProductFileJpaEntity::generationToDomain).collect(Collectors.toList()))
                .title(this.title)
                .userId(this.userId)
                .member(this.member.productToDomain())
                .likeCount(this.likeCount)
                .content(this.content)
                .price(this.price)
                .category(this.category)
                .views(this.views)
                .thumbnailUrl(this.thumbnailUrl)
                .createdAt(this.createAt)
                .modifiedAt(this.modifiedAt)
                .build();
    }

    public Product updateToDomain() {
        return Product.builder()
                .productSeq(this.productSeq)
                .images(this.images.stream().map(ProductFileJpaEntity::updateToDomain).collect(Collectors.toList()))
                .title(this.title)
                .userId(this.userId)
                .member(this.member.productToDomain())
                .content(this.content)
                .price(this.price)
                .likeCount(this.likeCount)
                .category(this.category)
                .views(this.views)
                .thumbnailUrl(this.thumbnailUrl)
                .createdAt(this.createAt)
                .modifiedAt(this.modifiedAt)
                .build();
    }

    public Product searchToDomain() {
        return Product.builder()
                .productSeq(this.productSeq)
                .images(this.images.stream().map(ProductFileJpaEntity::searchToDomain).collect(Collectors.toList()))
                .title(this.title)
                .userId(this.userId)
                .member(this.member.productToDomain())
                .content(this.content)
                .price(this.price)
                .likeCount(this.likeCount)
                .category(this.category)
                .views(this.views)
                .thumbnailUrl(this.thumbnailUrl)
                .createdAt(this.createAt)
                .modifiedAt(this.modifiedAt)
                .build();
    }

    public Product chatToDomain() {
        return Product.builder()
                .productSeq(this.productSeq)
                .images(this.images.stream().map(ProductFileJpaEntity::searchToDomain).collect(Collectors.toList()))
                .title(this.title)
                .userId(this.userId)
                .member(this.member.productToDomain())
                .content(this.content)
                .price(this.price)
                .likeCount(this.likeCount)
                .category(this.category)
                .views(this.views)
                .thumbnailUrl(this.thumbnailUrl)
                .createdAt(this.createAt)
                .modifiedAt(this.modifiedAt)
                .build();
    }

    public Product likeToDomain() {
        return Product.builder()
                .productSeq(this.productSeq)
                .title(this.title)
                .userId(this.userId)
                .content(this.content)
                .price(this.price)
                .likeCount(this.likeCount)
                .category(this.category)
                .views(this.views)
                .thumbnailUrl(this.thumbnailUrl)
                .createdAt(this.createAt)
                .modifiedAt(this.modifiedAt)
                .build();
    }
}

