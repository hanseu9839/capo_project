package com.realworld.feature.member.service;

import com.realworld.feature.member.entity.MemberJpaEntity;
import com.realworld.feature.member.repository.CommandMemberPort;
import com.realworld.feature.member.repository.LoadMemberPort;
import com.realworld.global.code.ErrorCode;
import com.realworld.global.config.exception.CustomLoginExceptionHandler;
import com.realworld.global.config.exception.CustomMemberExceptionHandler;
import com.realworld.global.config.jwt.JwtTokenProvider;
import com.realworld.global.utils.CommonUtil;
import com.realworld.feature.auth.Authority;
import com.realworld.feature.member.domain.Member;
import com.realworld.feature.token.TokenCommandService;
import com.realworld.feature.token.Token;
import com.realworld.feature.token.TokenDTO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberCommandServiceImpl implements MemberCommandService{
    private final CommandMemberPort commandMemberPort;
    private final LoadMemberPort loadMemberPort;
    private final TokenCommandService commandTokenPort;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

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

    @Override
    public Token login(Member member) {
        // TODO: 수정해야 함
        String userId = member.getUserId();
        String password = member.getPassword();

        Member findMember = loadMemberPort.findByUserId(userId).orElseThrow(()
                -> new CustomLoginExceptionHandler(ErrorCode.NOT_EXISTS_USERID));

        //비밀번호가 불일치할 경우
        if(!passwordEncoder.matches(password, findMember.getPassword())){
            throw new CustomLoginExceptionHandler(ErrorCode.LOGIN_REQUEST_ERROR);
        }

        // 받아온 유저네임과 패스워드를 이용해 UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, password);

        // authenticationToken 객체를 통해 Authentication 생성
        // authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행된다.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        TokenDTO tokenDTO = jwtTokenProvider.createToken(authentication);
        tokenDTO.setUserId(userId);

        Token token = Token.builder()
                            .accessToken(tokenDTO.getAccessToken())
                            .refreshToken(tokenDTO.getRefreshToken())
                            .grantType(tokenDTO.getGrantType())
                            .userId(userId)
                            .nickname(member.getNickname())
                            .build();

        commandTokenPort.saveToken(token);

        return token;
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
