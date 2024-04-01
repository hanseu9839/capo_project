package com.realworld.feature.token;

import java.util.Optional;

import com.realworld.feature.token.entity.TokenJpaEntity;

public interface LoadTokenPort {
    Optional<TokenJpaEntity> findByUserId(String userId);
    void deleteToken(String userId);

}
