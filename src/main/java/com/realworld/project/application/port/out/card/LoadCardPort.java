package com.realworld.project.application.port.out.card;

import com.realworld.project.domain.Card;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface LoadCardPort {
    List<Card> getSearchCardList(Pageable pageable, String search, String category, long seq);
}
