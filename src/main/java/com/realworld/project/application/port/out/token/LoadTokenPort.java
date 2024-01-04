package com.realworld.project.application.port.out.token;

import com.realworld.project.adapter.out.persistence.token.TokenJpaEntity;

import java.util.Optional;

public interface LoadTokenPort {
    Optional<TokenJpaEntity> findByUserId(String userId);
    void deleteToken(String userId);

}
