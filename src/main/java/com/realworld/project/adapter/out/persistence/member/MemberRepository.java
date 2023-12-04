package com.realworld.project.adapter.out.persistence.member;

import com.realworld.project.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberJpaEntity, Long> {

}