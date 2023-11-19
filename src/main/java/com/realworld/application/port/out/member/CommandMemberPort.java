package com.realworld.application.port.out.member;

import com.realworld.domain.Member;

public interface CommandMemberPort {
    void saveMember(Member member);
}
