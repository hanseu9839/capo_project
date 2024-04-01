package com.realworld.feature.card;

import com.realworld.feature.card.entity.CardJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardJpaEntity, String>, CardRepositoryCustom{
}
