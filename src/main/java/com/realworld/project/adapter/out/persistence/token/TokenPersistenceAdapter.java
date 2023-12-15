package com.realworld.project.adapter.out.persistence.token;

import com.realworld.project.application.port.out.token.CommandTokenPort;
import com.realworld.project.application.port.out.token.LoadTokenPort;
import com.realworld.project.domain.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TokenPersistenceAdapter implements CommandTokenPort, LoadTokenPort {
    private final TokenMapper tokenMapper;
    private final TokenRepository tokenRepository;
    @Override
    public void saveToken(Token token) {
        log.info("token userId : {}", token.getUserId());

        TokenJpaEntity entity = tokenMapper.toEntity(token);
        tokenRepository.save(entity);
    }

    @Override
    public Optional<TokenJpaEntity> findByUserId(String userId){ return tokenRepository.findByUserId(userId); }
}
