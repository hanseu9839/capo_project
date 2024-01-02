package com.realworld.project.application.port.in.profile;

import com.realworld.project.application.port.in.dto.MemberDTO;
import com.realworld.project.domain.Member;

public interface PostProfileUseCase {
    Member nicknameUpdate(MemberDTO memberDto, String userId);
}
