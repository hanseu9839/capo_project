package com.realworld.project.service;

import com.realworld.project.application.port.in.GetMemberUseCase;
import com.realworld.project.application.port.in.PostMemberUseCase;
import com.realworld.project.application.port.in.dto.MemberDTO;
import com.realworld.project.application.port.in.dto.TokenDTO;
import com.realworld.project.application.port.out.member.CommandMemberPort;
import com.realworld.project.application.port.out.member.LoadMemberPort;
import com.realworld.project.domain.Authority;
import com.realworld.project.domain.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

@Primary
@Service
@RequiredArgsConstructor
public class MemberService implements PostMemberUseCase , GetMemberUseCase , UserDetailsService {
    private final CommandMemberPort commandMemberPort;
    private final LoadMemberPort loadMemberPort;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
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

    @Override
    public ResponseEntity login(MemberDTO memberDTO) {
        String userId = memberDTO.getUserId();
        String password = memberDTO.getPassword();

        // 받아온 유저네임과 패스워드를 이용해 UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, password);

        // authenticationToken 객체를 통해 Authentication 생성
        // 이
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return null;
    }

    @Override
    public Member findByUserId(String userId) {
        Member member = loadMemberPort.findByUserId(userId);
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
        Optional<Member> member = Optional.ofNullable(loadMemberPort.findByUserId(username));
        return member.map(this::createUserDetails).orElseThrow(()-> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));

    }

    // DB 에 User 값이 존재하면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member){
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getAuthority().toString());

        return new User(
          String.valueOf(member.getUserId()),
          member.getPassword(),
          Collections.singleton(grantedAuthority)
        );
    }

}
