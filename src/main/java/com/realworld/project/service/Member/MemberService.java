package com.realworld.project.service.Member;

import com.realworld.project.application.port.in.Member.GetMemberUseCase;
import com.realworld.project.application.port.in.Member.PostMemberUseCase;
import com.realworld.project.application.port.in.Token.PostTokenUseCase;
import com.realworld.project.application.port.in.dto.MemberDTO;
import com.realworld.project.application.port.in.dto.TokenDTO;
import com.realworld.project.application.port.out.member.CommandMemberPort;
import com.realworld.project.application.port.out.member.LoadMemberPort;
import com.realworld.project.application.port.out.token.CommandTokenPort;
import com.realworld.project.common.Code.ErrorCode;
import com.realworld.project.common.config.exception.CustomJwtExceptionHandler;
import com.realworld.project.common.config.exception.CustomLoginExceptionHandler;
import com.realworld.project.common.config.jwt.JwtTokenProvider;
import com.realworld.project.common.response.ApiResponse;
import com.realworld.project.domain.Authority;
import com.realworld.project.domain.Member;
import com.realworld.project.domain.Token;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class MemberService implements PostMemberUseCase , GetMemberUseCase , PostTokenUseCase, UserDetailsService {
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

        //비밀번호가 불 일치할 경우
        if(!password.equals(member.get().getPassword())){
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


   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username : {} ",username);

        return  loadMemberPort.findByUserId(username)
                .map(this::createUserDetails)
                .orElseThrow(()-> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));

    }

    // DB 에 User 값이 존재하면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member){
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getAuthority().toString());
        log.info("Collections.singleton : {}", Collections.singleton(grantedAuthority));
        log.info("member getUserId : {} ", member.getUserId());
        log.info("member getUserEmail : {}", member.getUserEmail());
        log.info("member getPassword : {} ", member.getPassword());
        return new User(
          String.valueOf(member.getUserId()),
          member.getPassword(),
          Collections.singleton(grantedAuthority)
        );
    }
    @Transactional
    @Override
    public ResponseEntity reissue(TokenDTO tokenDto) {
        if(!jwtTokenProvider.validateToken(tokenDto.getRefreshToken())){
            throw new CustomJwtExceptionHandler(ErrorCode.JWT_TOKEN_REQUEST_ERROR);
        }

        // 2. Access Token에서 Member ID 가져오기
        Authentication authentication = jwtTokenProvider.getAuthentication(tokenDto.getAccessToken());

        log.info("authentication == {}", authentication.getName());
        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        Token token = Token.builder()
                            .userId(authentication.getName())
                            .refreshToken(tokenDto.getRefreshToken())
                            .build();
        return ApiResponse.success();
    }
}
