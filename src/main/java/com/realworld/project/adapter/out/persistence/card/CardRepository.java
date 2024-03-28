package com.realworld.project.adapter.out.persistence.card;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardJpaEntity, String>, CardRepositoryCustom{
}
