package com.realworld.feature.like.entity;

import com.realworld.feature.like.domain.Like;
import com.realworld.feature.member.entity.MemberJpaEntity;
import com.realworld.feature.product.entity.ProductJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Table(name = "like")
@ToString
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class LikeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_seq")
    private Long likeSeq;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private MemberJpaEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_seq", referencedColumnName = "product_seq")
    private ProductJpaEntity product;

    public Like toDomain() {
        return Like.builder()
                .likeSeq(this.likeSeq)
                .member(this.member.toDomain())
                .product(this.product.toDomain())
                .build();
    }

    public Like getProductToDomain() {
        return Like.builder()
                .likeSeq(this.likeSeq)
                .member(this.member.productToDomain())
                .product(this.product.likeToDomain())
                .build();
    }
}
