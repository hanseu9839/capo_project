package com.realworld.feature.profile.service;

import com.realworld.feature.member.MemberDTO;
import com.realworld.feature.member.domain.Member;
import com.realworld.feature.profile.controller.request.UpdateNickNameRequest;

public interface ProfileCommandService {
    Member nicknameUpdate(UpdateNickNameRequest request, String userId);
}
