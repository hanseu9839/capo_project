package com.realworld.project.service;

import com.realworld.project.application.port.in.PostMemberUseCase;
import com.realworld.project.application.port.in.dto.MemberDTO;
import com.realworld.project.application.port.out.member.CommandMemberPort;
import com.realworld.project.domain.Authority;
import com.realworld.project.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Primary
@Service
@RequiredArgsConstructor
public class MemberService implements PostMemberUseCase {
    private final CommandMemberPort commandMemberPort;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void saveMember(MemberDTO memberDto) {
        Member member = Member.builder()
                            .userId(memberDto.getUserId())
                            .password(passwordEncoder.encode(memberDto.getPassword()))
                            .phoneNumber(memberDto.getPhoneNumber())
                            .userEmail(memberDto.getUserEmail())
                            .delYn("N")
                            .authority(Authority.ROLE_USER)
                            .build();
        commandMemberPort.saveMember(member);
    }


}
