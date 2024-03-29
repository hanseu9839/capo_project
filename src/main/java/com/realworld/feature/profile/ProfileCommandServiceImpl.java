package com.realworld.feature.profile;

import com.realworld.global.code.ErrorCode;
import com.realworld.global.config.exception.CustomMemberExceptionHandler;
import com.realworld.feature.member.repository.CommandMemberPort;
import com.realworld.feature.member.MemberDTO;
import com.realworld.feature.member.domain.Member;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final CommandMemberPort commandMemberPort;
    @Transactional
    @Override
    public Member nicknameUpdate(MemberDTO memberDto, String userId) {
        Member member = Member.builder()
                            .nickname(memberDto.getNickname())
                            .userId(userId)
                            .build();
        long update = -1;
        if(!StringUtils.isEmpty(memberDto.getNickname())) update = commandMemberPort.nicknameUpdate(member);

        if(update<0) throw new CustomMemberExceptionHandler(ErrorCode.BAD_REQUEST_ERROR);

        return member;
    }
}
