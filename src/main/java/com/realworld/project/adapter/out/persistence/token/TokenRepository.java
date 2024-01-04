package com.realworld.project.adapter.out.persistence.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenJpaEntity, Long> {
        Optional<TokenJpaEntity> findByUserId(String userId);

}
