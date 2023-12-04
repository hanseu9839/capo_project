package com.realworld.project.application.port.out.member;

import com.realworld.project.domain.Member;
import com.realworld.project.domain.Token;

public interface CommandMemberPort {
    void saveMember(Member member);
    Token login(Member member);

}
