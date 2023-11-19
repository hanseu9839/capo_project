package com.realworld.project.application.port.in;

import com.realworld.project.domain.Member;

public interface PostMemberUseCase {
    void saveMember(Member member);
}
