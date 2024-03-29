package com.realworld.feature.card;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * TODO: CardJpaEntity Image, Like 좋아요 기능 컬럼 미구현 (추후 개발)
 */
@Table(name="card")
@ToString
@Entity
@NoArgsConstructor
@Setter @Getter
@EntityListeners(AuditingEntityListener.class)
public class CardJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardSeq;
    private String title;
    private String content;
    private Long price;
    private String category;
    private int views;
    private LocalDateTime createDt;
    private LocalDateTime regDt;

    @Builder
    public CardJpaEntity(Long cardSeq, String title, String content, Long price, String category,int views, LocalDateTime createDt, LocalDateTime regDt){
        this.cardSeq = cardSeq;
        this.title = title;
        this.content = content;
        this.price = price;
        this.category = category;
        this.views = views;
        this.createDt = createDt;
        this.regDt = regDt;
    }
}
