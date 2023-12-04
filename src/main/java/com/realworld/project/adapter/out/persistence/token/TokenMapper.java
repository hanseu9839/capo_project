package com.realworld.project.adapter.out.persistence.token;

import com.realworld.project.domain.Token;
import org.springframework.stereotype.Component;

@Component
public class TokenMapper {
    public Token toDomain(TokenJpaEntity tokenJpaEntity){
        return Token.builder()
                .grantType(tokenJpaEntity.getGrantType())
                .accessToken(tokenJpaEntity.getAccessToken())
                .refreshToken(tokenJpaEntity.getRefeshToken())
                .userId(tokenJpaEntity.getUserId())
                .build();
    }

    public TokenJpaEntity toEntity(Token token){
        return TokenJpaEntity.builder()
                            .grantType(token.getGrantType())
                            .accessToken(token.getAccessToken())
                            .refeshToken(token.getRefreshToken())
                            .userId(token.getUserId())
                            .build();
    }
}
