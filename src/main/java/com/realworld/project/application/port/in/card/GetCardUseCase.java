package com.realworld.project.application.port.in.card;

import com.realworld.project.application.port.in.dto.CardDto;
import com.realworld.project.domain.Card;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface GetCardUseCase {
    List<Card> getSearchCardList(final Pageable pageable, String search, String category, final long idx);
}
