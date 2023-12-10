package com.realworld.project.application.port.out.member;

import com.realworld.project.domain.Member;

import java.util.Optional;

public interface LoadMemberPort {
    Optional<Member> findByUserId(String userId);
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserId(String userId);
}
