package com.realworld.project.adapter.out.persistence.member;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberJpaEntity, String>, MemberRepositoryCustom{
    MemberJpaEntity findByUserId(String userId);
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserId(String userId);
}
