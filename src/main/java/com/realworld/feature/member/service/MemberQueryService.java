package com.realworld.feature.member.service;

import java.util.Optional;

import com.realworld.feature.member.domain.Member;

public interface MemberQueryService {
    Optional<Member> getMemberByUserId(String userId);
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserId(String userId);
    Member findByUserEmail(String userEmail);

}
