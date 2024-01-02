package com.realworld.project.service.profile;

import com.realworld.project.adapter.out.persistence.member.MemberJpaEntity;
import com.realworld.project.application.port.in.dto.MemberDTO;
import com.realworld.project.application.port.in.profile.GetProfileUseCase;
import com.realworld.project.application.port.in.profile.PostProfileUseCase;
import com.realworld.project.application.port.out.member.CommandMemberPort;
import com.realworld.project.application.port.out.member.LoadMemberPort;
import com.realworld.project.application.port.out.token.CommandTokenPort;
import com.realworld.project.common.code.ErrorCode;
import com.realworld.project.common.config.exception.CustomMemberExceptionHandler;
import com.realworld.project.common.config.jwt.JwtTokenProvider;
import com.realworld.project.domain.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService implements PostProfileUseCase, GetProfileUseCase {
    private final LoadMemberPort loadMemberPort;

    @Transactional
    @Override
    public Member nicknameUpdate(MemberDTO memberDto, String userId) {
        log.info("userId {} ", userId);
        Optional<MemberJpaEntity> member = loadMemberPort.findByUserId(userId);
        if(member.isPresent()){
            if(!StringUtils.isEmpty(memberDto.getNickname())) member.get().setNickname(memberDto.getNickname());
        } else {
            throw new CustomMemberExceptionHandler(ErrorCode.BAD_REQUEST_ERROR);
        }

        Member target = Member.builder()
                .nickname(member.get().getNickname())
                .build();
        log.info("nickname : {} ",target.getNickname());
        return target;
    }
}
