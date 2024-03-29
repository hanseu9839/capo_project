package com.realworld.feature.card;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GetCardUseCase {
    Slice<Card> getSearchCardList(final Pageable pageable, String search, String category, final long idx);
}
