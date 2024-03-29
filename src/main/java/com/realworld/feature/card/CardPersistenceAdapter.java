package com.realworld.feature.card;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CardPersistenceAdapter implements CommandCardPort, LoadCardPort {
    private final CardMapper cardMapper;
    private final CardRepository cardRepository;

    @Override
    public Slice<Card> getSearchCardList( Pageable pageable, String search, String category,long seq) {
        cardRepository.findAllCardList(pageable, search, category, seq);
        return null;
    }
}
