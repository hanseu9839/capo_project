package com.realworld.project.application.port.out.member;

import com.realworld.project.adapter.out.persistence.member.MemberJpaEntity;

import java.util.Optional;

public interface LoadMemberPort {
    Optional<MemberJpaEntity> findByUserId(String userId);
    Optional<MemberJpaEntity> findByUserEmail(String userEmail);
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserId(String userId);

}
