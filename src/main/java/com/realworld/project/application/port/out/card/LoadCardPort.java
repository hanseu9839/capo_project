package com.realworld.project.application.port.out.card;

import com.realworld.project.application.port.in.dto.CardDto;
import com.realworld.project.domain.Card;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface LoadCardPort {
    Slice<Card> getSearchCardList(Pageable pageable,String search, String category,long seq);
}
