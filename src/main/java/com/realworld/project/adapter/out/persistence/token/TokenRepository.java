package com.realworld.project.adapter.out.persistence.token;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenJpaEntity, Long> {
}
