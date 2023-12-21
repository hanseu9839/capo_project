package com.realworld.project.service.member;


import com.realworld.project.application.port.in.member.GetMemberUseCase;
import com.realworld.project.application.port.in.member.PostMemberUseCase;
import com.realworld.project.application.port.in.dto.MemberDTO;
import com.realworld.project.application.port.in.dto.TokenDTO;
import com.realworld.project.application.port.out.member.CommandMemberPort;
import com.realworld.project.application.port.out.member.LoadMemberPort;
import com.realworld.project.application.port.out.token.CommandTokenPort;
import com.realworld.project.common.code.ErrorCode;
import com.realworld.project.common.config.exception.CustomLoginExceptionHandler;
import com.realworld.project.common.config.jwt.JwtTokenProvider;
import com.realworld.project.domain.Authority;
import com.realworld.project.domain.Member;
import com.realworld.project.domain.Token;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class MemberService implements PostMemberUseCase , GetMemberUseCase{
    private final CommandMemberPort commandMemberPort;
    private final LoadMemberPort loadMemberPort;
    private final CommandTokenPort commandTokenPort;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;



    @Transactional
    @Override
    public void saveMember(MemberDTO memberDto) {

        Member member = Member.builder()
                            .userId(memberDto.getUserId())
                            .password(passwordEncoder.encode(memberDto.getPassword()))
                            .phoneNumber(memberDto.getPhoneNumber())
                            .userEmail(memberDto.getUserEmail())
                            .delYn("N")
                            .authority(Authority.ROLE_USER)
                            .build();
        commandMemberPort.saveMember(member);
    }

    @Transactional
    @Override
    public TokenDTO login(MemberDTO memberDTO) {
        String userId = memberDTO.getUserId();
        String password = memberDTO.getPassword();

        Optional<Member> member = findByUserId(userId);

        //비밀번호가 불일치할 경우
        if(!passwordEncoder.matches(password,member.get().getPassword())){
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
                            .build();

        log.info("token : {} ", token.getUserId());
        commandTokenPort.saveToken(token);

        return tokenDTO;
    }

    /**
     * UserId 가져오기
     * @param userId
     * @return
     */
    @Override
    public Optional<Member> findByUserId(String userId) {
        Optional<Member> member = loadMemberPort.findByUserId(userId);
        return member;
    }

    @Override
    public boolean existsByUserEmail(String userEmail) {
        return loadMemberPort.existsByUserEmail(userEmail);
    }

    @Override
    public boolean existsByUserId(String userId) {
        return loadMemberPort.existsByUserId(userId);
    }


}
