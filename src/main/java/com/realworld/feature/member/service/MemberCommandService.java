package com.realworld.feature.member.service;

import com.realworld.feature.member.domain.Member;

import java.util.Optional;

public interface MemberCommandService {
    Optional<Member> saveMember(Member memberDto);

    void remove(String userId, String password);

    long updatePassword(Member member);

    long updateEmail(Member member);
}
