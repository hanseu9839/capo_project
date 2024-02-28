package com.realworld.project.member;


import com.realworld.project.adapter.out.persistence.member.MemberJpaEntity;
import com.realworld.project.application.port.in.dto.MemberDTO;
import com.realworld.project.application.port.out.member.CommandMemberPort;
import com.realworld.project.application.port.out.member.LoadMemberPort;
import com.realworld.project.application.port.out.token.CommandTokenPort;
import com.realworld.project.common.config.QueryDslConfig;
import com.realworld.project.common.config.SecurityConfig;
import com.realworld.project.common.config.jwt.JwtTokenProvider;
import com.realworld.project.service.member.MemberService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(QueryDslConfig.class)
@ExtendWith(SpringExtension.class)
public class MemberServiceTest {
    @Autowired
    private CommandMemberPort commandMemberPort;
    @Autowired
    private LoadMemberPort loadMemberPort;
    @Autowired
    private CommandTokenPort commandTokenPort;
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private SecurityConfig securityConfig;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("패스워드 암호화 테스트")
    void passwordEncode(){
        // given
        String rawPassword = "12345678";

        // when
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // then
        assertAll(
                ()-> assertNotEquals(rawPassword, encodedPassword),
                ()->assertTrue(passwordEncoder.matches(rawPassword, encodedPassword))
        );
    }
//    @Test
//    @DisplayName("회원가입 테스트")
//    void saveMember() {
        //given
//        MemberService memberService = new MemberService(commandMemberPort, loadMemberPort, commandTokenPort,passwordEncoder, authenticationManagerBuilder, jwtTokenProvider);
//        MemberDTO member = MemberDTO.builder()
//                .userId("test8833")
//                .userEmail("hans983@naver.com")
//                .phoneNumber("01023599839")
//                .password("@Qwerty54953")
//                .build();build

        // when
//        MemberJpaEntity saveId = memberService.saveMember(member);

        // then
//        assertEquals(member.getUserId(), saveId.getUserId());
//    }

    @Test
    @Transactional
    @DisplayName("회원 비밀번호 변경")
    void changePassword(){
        // given
        MemberService memberService = new MemberService(commandMemberPort, loadMemberPort, commandTokenPort,passwordEncoder, authenticationManagerBuilder, jwtTokenProvider);
        MemberDTO member = MemberDTO.builder()
                                    .userEmail("hans983@naver.com")
                                    .userId("seoung1234")
                                    .newPassword("@Qwer1234")
                                    .build();
        // when
        long updated = memberService.updatePassword(member);

        //then
        assertTrue(updated>0);
    }
}
