package com.realworld.feature.card;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardJpaEntity, String>, CardRepositoryCustom{
}
