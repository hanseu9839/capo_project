package com.realworld.feature.card;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface CardRepositoryCustom  {
    List<CardJpaEntity> findAllCardList(Pageable pageable, String search, String category, long seq);
}
