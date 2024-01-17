package com.realworld.project.adapter.out.persistence.token;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public long updateToken(TokenJpaEntity tokenJpaEntity) {
        QTokenJpaEntity token = QTokenJpaEntity.tokenJpaEntity;
        long update = jpaQueryFactory.update(token)
                .set(token.refreshToken, tokenJpaEntity.getAccessToken())
                .set(token.accessToken, tokenJpaEntity.getAccessToken())
                .where(token.userId.eq(tokenJpaEntity.getUserId()))
                .execute();
        return update;
    }
}
