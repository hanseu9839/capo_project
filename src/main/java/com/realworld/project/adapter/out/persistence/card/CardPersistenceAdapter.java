package com.realworld.project.adapter.out.persistence.card;

import com.realworld.project.application.port.in.dto.CardDto;
import com.realworld.project.application.port.out.card.CommandCardPort;
import com.realworld.project.application.port.out.card.LoadCardPort;
import com.realworld.project.domain.Card;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
