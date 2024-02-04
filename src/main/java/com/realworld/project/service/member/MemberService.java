package com.realworld.project.service.member;


import com.realworld.project.adapter.out.persistence.member.MemberJpaEntity;
import com.realworld.project.application.port.in.member.GetMemberUseCase;
import com.realworld.project.application.port.in.member.PostMemberUseCase;
import com.realworld.project.application.port.in.dto.MemberDTO;
import com.realworld.project.application.port.in.dto.TokenDTO;
import com.realworld.project.application.port.out.member.CommandMemberPort;
import com.realworld.project.application.port.out.member.LoadMemberPort;
import com.realworld.project.application.port.out.token.CommandTokenPort;
import com.realworld.project.common.code.ErrorCode;
import com.realworld.project.common.config.exception.CustomLoginExceptionHandler;
import com.realworld.project.common.config.exception.CustomMemberExceptionHandler;
import com.realworld.project.common.config.jwt.JwtTokenProvider;
import com.realworld.project.common.utils.CommonUtil;
import com.realworld.project.domain.Authority;
import com.realworld.project.domain.Member;
import com.realworld.project.domain.Token;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
public class MemberService implements PostMemberUseCase , GetMemberUseCase{
    private final CommandMemberPort commandMemberPort;
    private final LoadMemberPort loadMemberPort;
    private final CommandTokenPort commandTokenPort;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public MemberService(CommandMemberPort commandMemberPort, LoadMemberPort loadMemberPort, CommandTokenPort commandTokenPort, PasswordEncoder passwordEncoder, AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider){
        this.commandMemberPort = commandMemberPort;
        this.loadMemberPort = loadMemberPort;
        this.commandTokenPort = commandTokenPort;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    @Override
    public MemberJpaEntity saveMember(MemberDTO memberDto) {
        if(!CommonUtil.passwordValidationCheck(memberDto.getPassword())){
            throw new CustomMemberExceptionHandler(ErrorCode.PASSWORD_REQUEST_ERROR);
        }
        if(!CommonUtil.userIdValidationCheck(memberDto.getUserId())){
            throw new CustomMemberExceptionHandler(ErrorCode.VALIDATION_USERID_ERROR);
        }

        Member member = Member.builder()
                            .userSeq(memberDto.getUserSeq())
                            .userId(memberDto.getUserId())
                            .password(passwordEncoder.encode(memberDto.getPassword()))
                            .phoneNumber(memberDto.getPhoneNumber())
                            .userEmail(memberDto.getUserEmail())
                            .nickname(CommonUtil.createNickname())
                            .delYn("N")
                            .authority(Authority.ROLE_USER)
                            .build();
        return commandMemberPort.saveMember(member);
    }

    @Transactional
    @Override
    public Token login(MemberDTO memberDTO) {
        String userId = memberDTO.getUserId();
        String password = memberDTO.getPassword();

        Optional<Member> member = findByUserId(userId);
        if(!member.isPresent()){
            throw new CustomLoginExceptionHandler(ErrorCode.NOT_EXISTS_USERID);
        }
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
                            .nickname(member.get().getNickname())
                            .build();

        log.info("token : {} ", token.getUserId());
        commandTokenPort.saveToken(token);

        return token;
    }
    @Transactional
    @Override
    public void remove(String userId, String password) {
        Optional<Member> targetMember = loadMemberPort.findByUserId(userId);

        Member member = Member.builder()
                                .userId(targetMember.get().getUserId())
                                .authority(targetMember.get().getAuthority())
                                .phoneNumber(targetMember.get().getPhoneNumber())
                                .userEmail(targetMember.get().getUserEmail())
                                .password(targetMember.get().getPassword())
                                .nickname(targetMember.get().getNickname())
                                .phoneNumber(targetMember.get().getPhoneNumber())
                                .build();

        if(passwordEncoder.matches(password, member.getPassword())){
            commandMemberPort.userRemove(targetMember.get());
            commandMemberPort.saveBackup(member);
        } else {
            throw new CustomMemberExceptionHandler(ErrorCode.VALIDATION_PASSWORD_ERROR);
        }
    }
    @Transactional
    @Override
    public long updatePassword(MemberDTO memberDTO) {
        Member target =this.findByUserEmail(memberDTO.getUserEmail());

        Member targetMember = Member.builder()
                .userId(target.getUserId())
                .password(passwordEncoder.encode(memberDTO.getNewPassword()))
                .build();

        long update = commandMemberPort.updatePassword(targetMember);
        if(update < 0) throw new CustomLoginExceptionHandler(ErrorCode.FAIL_PASSWORD_CHANGE);

        return update;
    }


    public Member findByUserEmail(String userEmail){
        Member member = loadMemberPort.findByUserEmail(userEmail);
        return member;
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
