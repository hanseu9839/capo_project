package com.realworld.project.adapter.out.persistence.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.realworld.project.domain.Member;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public long updatePassword(MemberJpaEntity memberJpaEntity) {
        QMemberJpaEntity member = QMemberJpaEntity.memberJpaEntity;
        long count = jpaQueryFactory
                .update(member)
                .set(member.password, memberJpaEntity.getPassword())
                .where(member.userId.eq(memberJpaEntity.getUserId()))
                .execute();
        return count;
    }


}

