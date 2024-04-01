package com.realworld.feature.member.service;

import com.realworld.feature.member.domain.Member;
import com.realworld.feature.token.Token;

public interface MemberCommandService {
    Member saveMember(Member memberDto);
    Token login(Member member);
    void remove(String userId, String password);
    long updatePassword(Member member);
    long updateEmail(Member member);
}
