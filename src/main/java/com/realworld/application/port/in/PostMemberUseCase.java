package com.realworld.application.port.in;

import com.realworld.domain.Member;

public interface PostMemberUseCase {
    void saveMember(Member member);
}
