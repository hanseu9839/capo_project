package com.realworld.feature.card;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface LoadCardPort {
    List<Card> getSearchCardList(Pageable pageable, String search, String category, long seq);
}
