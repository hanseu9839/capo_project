package com.realworld.project.application.port.out.member;

import com.realworld.project.domain.Member;

public interface CommandMemberPort {
    void saveMember(Member member);
}
