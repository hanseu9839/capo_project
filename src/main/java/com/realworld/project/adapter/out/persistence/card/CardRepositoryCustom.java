package com.realworld.project.adapter.out.persistence.card;

import com.realworld.project.application.port.in.dto.CardDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepositoryCustom  {
    List<CardJpaEntity> findAllCardList(Pageable pageable, String search, String category, long seq);
}
