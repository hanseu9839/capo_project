package com.realworld.project.application.port.out.member;

import com.realworld.project.adapter.out.persistence.member.MemberJpaEntity;
import com.realworld.project.domain.Member;

import java.util.Optional;

public interface LoadMemberPort {
    Optional<Member> findByUserId(String userId);
    Member findByUserEmail(String userEmail);
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserId(String userId);

}
