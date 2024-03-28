package com.realworld.project.application.port.in.card;

import com.realworld.project.application.port.in.dto.CardDto;
import com.realworld.project.domain.Card;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GetCardUseCase {
    Slice<Card> getSearchCardList(final Pageable pageable, String search, String category, final long idx);
}
