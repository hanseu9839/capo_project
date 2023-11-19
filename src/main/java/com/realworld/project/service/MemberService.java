package com.realworld.project.service;

import com.realworld.project.application.port.in.PostMemberUseCase;
import com.realworld.project.application.port.out.member.CommandMemberPort;
import com.realworld.project.domain.Member;
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
