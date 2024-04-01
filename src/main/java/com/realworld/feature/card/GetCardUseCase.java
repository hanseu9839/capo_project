package com.realworld.feature.card;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface GetCardUseCase {
    List<Card> getSearchCardList(final Pageable pageable, String search, String category, final long idx);
}
