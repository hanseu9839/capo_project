package com.realworld.project.application.port.in.Member;

import com.realworld.project.application.port.in.dto.MemberDTO;
import com.realworld.project.application.port.in.dto.TokenDTO;
import com.realworld.project.domain.Member;
import org.springframework.http.ResponseEntity;

public interface PostMemberUseCase {
    void saveMember(MemberDTO memberDto);
    TokenDTO login(MemberDTO memberDto);

}
