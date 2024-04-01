package com.realworld.feature.card;

import com.realworld.feature.card.domain.Card;
import com.realworld.feature.card.entity.CardJpaEntity;
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
