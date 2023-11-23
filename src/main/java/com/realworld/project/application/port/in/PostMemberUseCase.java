package com.realworld.project.application.port.in;

import com.realworld.project.application.port.in.dto.MemberDTO;
import com.realworld.project.domain.Member;

public interface PostMemberUseCase {
    void saveMember(MemberDTO memberDto);
}
