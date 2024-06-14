package com.realworld.feature.like.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.realworld.feature.like.domain.Like;
import com.realworld.feature.like.entity.LikeJpaEntity;
import com.realworld.feature.like.entity.QLikeJpaEntity;
import com.realworld.feature.member.entity.QMemberJpaEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class LikeRepositoryCustomImpl implements LikeRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QLikeJpaEntity like = QLikeJpaEntity.likeJpaEntity;
    private final QMemberJpaEntity member = QMemberJpaEntity.memberJpaEntity;

    public List<Like> findUserProductLikes(String userId) {
        List<LikeJpaEntity> likes = queryFactory.select(like)
                .from(like)
                .innerJoin(like.member, member)
                .where(
                        like.member.userId.eq(userId)
                ).fetch();
        return likes.stream().map(LikeJpaEntity::getProductToDomain).toList();
    }

}
