package com.realworld.project.adapter.out.persistence.member;

import com.realworld.project.domain.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {
    long updatePassword(MemberJpaEntity memberJpaEntity);
}
