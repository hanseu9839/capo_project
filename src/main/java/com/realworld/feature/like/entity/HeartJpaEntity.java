//package com.realworld.feature.like.entity;
//
//import com.realworld.feature.member.entity.MemberJpaEntity;
//import com.realworld.feature.product.entity.ProductJpaEntity;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//
//@Table(name = "heart")
//@ToString
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
//public class HeartJpaEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "heart_seq")
//    private Long heartSeq;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//    private MemberJpaEntity member;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
//    private ProductJpaEntity product;
//}
