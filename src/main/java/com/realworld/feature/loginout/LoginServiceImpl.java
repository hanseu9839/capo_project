package com.realworld.feature.loginout;

import com.realworld.feature.member.domain.Member;
import com.realworld.feature.member.service.MemberQueryService;
import com.realworld.feature.token.Token;
import com.realworld.feature.token.service.TokenCommandService;
import com.realworld.feature.token.TokenDTO;
import com.realworld.global.code.ErrorCode;
import com.realworld.global.config.exception.CustomLoginExceptionHandler;
import com.realworld.global.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final MemberQueryService memberQueryService;
    private final PasswordEncoder passwordEncoder;
    private final TokenCommandService tokenCommandService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Token loginAndGetToken(Member member) {
        String userId = member.getUserId();
        String password = member.getPassword();

        Member findMember = memberQueryService.getMemberByUserId(userId).orElseThrow(()
                -> new CustomLoginExceptionHandler(ErrorCode.NOT_EXISTS_USERID));

        //비밀번호가 불일치할 경우
        if(!passwordEncoder.matches(password, findMember.getPassword())){
            throw new CustomLoginExceptionHandler(ErrorCode.LOGIN_REQUEST_ERROR);
        }

        // 받아온 유저네임과 패스워드를 이용해 UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userId, password);

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

        Token savedToken = tokenCommandService.saveToken(token);
        savedToken.setNickname(member.getNickname());

        return savedToken;
    }
}
