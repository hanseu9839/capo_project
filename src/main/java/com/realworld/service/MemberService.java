package com.realworld.service;

import com.realworld.adapter.out.persistence.member.MemberMapper;
import com.realworld.application.port.in.PostMemberUseCase;
import com.realworld.application.port.out.member.CommandMemberPort;
import com.realworld.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
@RequiredArgsConstructor
public class MemberService implements PostMemberUseCase {
    private final CommandMemberPort commandMemberPort;

    @Override
    public void saveMember(Member member) {
        commandMemberPort.saveMember(member);
    }
}
