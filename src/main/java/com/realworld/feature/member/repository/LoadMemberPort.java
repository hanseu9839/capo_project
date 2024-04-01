package com.realworld.feature.member.repository;

import java.util.Optional;

import com.realworld.feature.member.domain.Member;

public interface LoadMemberPort {
    Optional<Member> findByUserId(String userId);
    Member findByUserEmail(String userEmail);
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserId(String userId);

}
