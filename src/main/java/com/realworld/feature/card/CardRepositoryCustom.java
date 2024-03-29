package com.realworld.feature.card;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CardRepositoryCustom  {
    Slice<CardJpaEntity> findAllCardList(Pageable pageable, String search, String category, long seq);
}
