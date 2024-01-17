package com.realworld.project.adapter.out.persistence.member;

import com.realworld.project.domain.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {
    MemberJpaEntity findByUserEmail(MemberJpaEntity memberJpaEntity);
    long updatePassword(MemberJpaEntity memberJpaEntity);
    long updateEmail(MemberJpaEntity memberJpaEntity);
    long updateNickname(MemberJpaEntity memberJpaEntity);
}
