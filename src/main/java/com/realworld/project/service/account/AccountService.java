package com.realworld.project.service.account;

import com.realworld.project.adapter.out.persistence.member.MemberJpaEntity;
import com.realworld.project.application.port.in.account.GetAccountUseCase;
import com.realworld.project.application.port.in.account.PostAccountUseCase;
import com.realworld.project.application.port.in.dto.MemberDTO;
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
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService implements GetAccountUseCase, PostAccountUseCase {
    private final CommandMemberPort commandMemberPort;
    private final LoadMemberPort loadMemberPort;
    private final CommandTokenPort commandTokenPort;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Member getAccount(String userId) {
        Optional<MemberJpaEntity> member = loadMemberPort.findByUserId(userId);
        Member target = Member.builder()
                .nickname(member.get().getNickname())
                .phoneNumber(member.get().getPhoneNumber())
                .userEmail(member.get().getUserEmail())
                .build();
        return target;
    }

    @Transactional
    @Override
    public void passwordUpdate(MemberDTO memberDto, String userId) {
        String currentPassword = memberDto.getCurrentPassword();
        String newPassword = memberDto.getNewPassword();
        log.info("userId : {} ", userId);
        log.info("new Password : {} ",newPassword);
        log.info("current Password:{} ",currentPassword);
        Optional<MemberJpaEntity> member = loadMemberPort.findByUserId(userId);

        // 현재 비밀번호 입력과 DB 비밀번호 비교 후 없으면 Exception
        if(!passwordEncoder.matches(currentPassword, member.get().getPassword())){
            throw new CustomMemberExceptionHandler(ErrorCode.VALIDATION_PASSWORD_ERROR);
        }

        // 존재하는 경우
        if(member.isPresent()){
            if(!StringUtils.isEmpty(memberDto.getNewPassword())) member.get().setPassword(passwordEncoder.encode(newPassword));
        } else{ // 존재하지 않으면 USER_ID와 매칭되는 값이 없음
            throw new CustomMemberExceptionHandler(ErrorCode.NOT_EXISTS_USERID);
        }

    }
    @Transactional
    @Override
    public Member emailUpdate(MemberDTO memberDto) {
        Optional<MemberJpaEntity> target = loadMemberPort.findByUserEmail(memberDto.getUserEmail());
        target.ifPresent(member-> member.setUserEmail(memberDto.getUserEmail()));
        Member member = Member.builder()
                            .userEmail(target.get().getUserEmail())
                            .build();
        return Member;
    }
}
