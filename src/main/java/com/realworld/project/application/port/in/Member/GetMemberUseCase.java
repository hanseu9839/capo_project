package com.realworld.project.application.port.in.Member;

import com.realworld.project.domain.Member;

import java.util.List;
import java.util.Optional;

public interface GetMemberUseCase {
    Optional<Member> findByUserId(String userId);
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserId(String userId);
}
