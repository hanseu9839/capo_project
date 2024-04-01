package com.realworld.feature.card;

import com.realworld.feature.card.domain.Card;
import com.realworld.feature.card.entity.CardJpaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CardPersistenceAdapter implements CommandCardPort, LoadCardPort {
    private final CardMapper cardMapper;
    private final CardRepository cardRepository;

    @Override
    public List<Card> getSearchCardList(Pageable pageable, String search, String category, long seq) {
        List<CardJpaEntity> cards = cardRepository.findAllCardList(pageable, search, category, seq);
        List<Card> convertList = new ArrayList<>();
        cards.forEach(card -> convertList.add(cardMapper.toDomain(card)));
        return convertList;
    }
}
