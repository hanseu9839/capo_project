package com.realworld.feature.auth;

import com.realworld.feature.member.domain.Member;
import com.realworld.feature.member.repository.LoadMemberPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final LoadMemberPort loadMemberPort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username : {} ", username);

        return loadMemberPort.findByUserId(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));

    }

    // DB 에 User 값이 존재하면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member) {
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
}
