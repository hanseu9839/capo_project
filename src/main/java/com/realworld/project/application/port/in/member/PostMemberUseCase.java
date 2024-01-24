package com.realworld.project.application.port.in.member;

import com.realworld.project.adapter.out.persistence.member.MemberJpaEntity;
import com.realworld.project.application.port.in.dto.MemberDTO;
import com.realworld.project.application.port.in.dto.TokenDTO;
import com.realworld.project.domain.Member;
import com.realworld.project.domain.Token;


public interface PostMemberUseCase {
    long saveMember(MemberDTO memberDto);
    Token login(MemberDTO memberDto);
    void remove(String userId, String password);
    long updatePassword(MemberDTO memberDTO);
}
