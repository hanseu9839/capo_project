package com.realworld.feature.card;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class CardService implements GetCardUseCase {

    private final LoadCardPort loadCardPort;

    @Override
    public Slice<Card> getSearchCardList(Pageable pageable, String search, String category, long seq) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
        loadCardPort.getSearchCardList(pageRequest, search, category , seq);
        return null;
    }


}
