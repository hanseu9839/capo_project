package com.realworld.feature.card;

import com.realworld.feature.card.domain.Card;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService implements GetCardUseCase {
    private final LoadCardPort loadCardPort;

    @Override
    public List<Card> getSearchCardList(Pageable pageable, String search, String category, long seq) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());

        return loadCardPort.getSearchCardList(pageRequest, search, category , seq);
    }


}
