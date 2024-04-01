package com.realworld.feature.member.service;

import com.realworld.feature.member.domain.Member;

import java.util.Optional;

public interface MemberQueryService {

    Optional<Member> getMemberByUserId(String userId);

    boolean existsByUserEmail(String userEmail);

    boolean existsByUserId(String userId);

    Member findByUserEmail(String userEmail);
}
