package com.realworld.feature.card;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface LoadCardPort {
    Slice<Card> getSearchCardList(Pageable pageable,String search, String category,long seq);
}
