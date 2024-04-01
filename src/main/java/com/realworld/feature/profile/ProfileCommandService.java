package com.realworld.feature.profile;

import com.realworld.feature.member.MemberDTO;
import com.realworld.feature.member.domain.Member;

public interface ProfileCommandService {
    Member nicknameUpdate(MemberDTO memberDto, String userId);
}
