package com.realworld.feature.card;

import com.realworld.feature.card.domain.Card;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetCardUseCase {
    List<Card> getSearchCardList(final Pageable pageable, String search, String category, final long idx);
}
