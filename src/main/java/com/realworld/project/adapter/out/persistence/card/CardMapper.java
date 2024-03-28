package com.realworld.project.adapter.out.persistence.card;

import com.realworld.project.domain.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {
    public Card toDomain(CardJpaEntity entity){
        return Card.builder()
                .title(entity.getTitle())
                .price(entity.getPrice())
                .views(entity.getViews())
                .content(entity.getContent())
                .regDt(entity.getRegDt())
                .createDt(entity.getCreateDt())
                .build();
    }

    public CardJpaEntity toEntity(Card domain){
        return CardJpaEntity.builder()
                .title(domain.getTitle())
                .price(domain.getPrice())
                .views(domain.getViews())
                .content(domain.getContent())
                .regDt(domain.getRegDt())
                .createDt(domain.getCreateDt())
                .build();
    }
}
