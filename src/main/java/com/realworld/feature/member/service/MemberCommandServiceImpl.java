package com.realworld.feature.member.service;

import com.realworld.feature.member.entity.MemberJpaEntity;
import com.realworld.feature.member.repository.CommandMemberPort;
import com.realworld.feature.member.repository.LoadMemberPort;
import com.realworld.global.code.ErrorCode;
import com.realworld.global.config.exception.CustomLoginExceptionHandler;
import com.realworld.global.config.exception.CustomMemberExceptionHandler;
import com.realworld.global.utils.CommonUtil;
import com.realworld.feature.auth.Authority;
import com.realworld.feature.member.domain.Member;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberCommandServiceImpl implements MemberCommandService{
    private final CommandMemberPort commandMemberPort;
    private final LoadMemberPort loadMemberPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member saveMember(Member member) {
        if(!CommonUtil.passwordValidationCheck(member.getPassword())){
            throw new CustomMemberExceptionHandler(ErrorCode.PASSWORD_REQUEST_ERROR);
        }
        if(!CommonUtil.userIdValidationCheck(member.getUserId())){
            throw new CustomMemberExceptionHandler(ErrorCode.VALIDATION_USERID_ERROR);
        }

        Member registMember = Member.builder()
                            .userSeq(member.getUserSeq())
                            .userId(member.getUserId())
                            .password(passwordEncoder.encode(member.getPassword()))
                            .phoneNumber(member.getPhoneNumber())
                            .userEmail(member.getUserEmail())
                            .nickname(CommonUtil.createNickname())
                            .delYn("N")
                            .authority(Authority.ROLE_USER)
                            .build();

        MemberJpaEntity memberJpaEntity = commandMemberPort.saveMember(registMember);

        return memberJpaEntity.toDomain();
    }

    @Transactional
    @Override
    public void remove(String userId, String password) {
        Member targetMember = loadMemberPort.findByUserId(userId).orElseThrow(()
                -> new CustomLoginExceptionHandler(ErrorCode.NOT_EXISTS_USERID));

        Member member = Member.builder()
                                .userId(targetMember.getUserId())
                                .authority(targetMember.getAuthority())
                                .phoneNumber(targetMember.getPhoneNumber())
                                .userEmail(targetMember.getUserEmail())
                                .password(targetMember.getPassword())
                                .nickname(targetMember.getNickname())
                                .phoneNumber(targetMember.getPhoneNumber())
                                .build();

        if(passwordEncoder.matches(password, member.getPassword())){
            commandMemberPort.userRemove(targetMember);
            commandMemberPort.saveBackup(member);
        } else {
            throw new CustomMemberExceptionHandler(ErrorCode.NOT_EQUAL_PASSWORD);
        }
    }

    @Override
    public long updatePassword(Member member) {
        // TODO: 비밀번호 찾기로 비밀번호 변경, 로그인해서 비밀번호 변경 분리하는 게 좋을 듯..?

        String currentPassword = member.getCurrentPassword();
        String newPassword = member.getNewPassword();

        if(StringUtils.isNotEmpty(member.getUserEmail())) {
            member = loadMemberPort.findByUserEmail(member.getUserEmail());
            // TODO: member 없으면 exception 반환
        }
        if(StringUtils.isNotEmpty(member.getUserId())) {
            member = loadMemberPort.findByUserId(member.getUserId()).orElseThrow(() ->
                    new CustomMemberExceptionHandler(ErrorCode.NOT_EXISTS_USERID));
        }

        if(StringUtils.isNotEmpty(currentPassword)
                && !passwordEncoder.matches(currentPassword, newPassword)){
            throw new CustomMemberExceptionHandler(ErrorCode.NOT_EQUAL_PASSWORD);
        }

        Member targetMember = Member.builder()
                .userId(member.getUserId())
                .password(passwordEncoder.encode(newPassword))
                .build();

        return commandMemberPort.updatePassword(targetMember);
    }

    @Override
    public long updateEmail(Member member) {
        return commandMemberPort.updateEmail(member);
    }
}
