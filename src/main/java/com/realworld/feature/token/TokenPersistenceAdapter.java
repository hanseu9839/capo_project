package com.realworld.feature.token;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import com.realworld.feature.token.entity.TokenJpaEntity;
import com.realworld.feature.token.repository.TokenRepository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TokenPersistenceAdapter implements TokenCommandService, LoadTokenPort {
    private final TokenRepository tokenRepository;
    @Override
    public void saveToken(Token token) {
        log.info("token userId : {}", token.getUserId());

        TokenJpaEntity entity = token.toEntity();
        tokenRepository.save(entity);
    }

    @Override
    public Optional<TokenJpaEntity> findByUserId(String userId){ return tokenRepository.findByUserId(userId); }

    @Override
    public void deleteToken(String userId) {
        TokenJpaEntity entity = TokenJpaEntity.builder()
                                    .userId(userId)
                                    .build();
        tokenRepository.delete(entity);
    }
}
