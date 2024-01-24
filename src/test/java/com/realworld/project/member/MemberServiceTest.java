//package com.realworld.project.member;
//
//
//import com.realworld.project.application.port.in.dto.MemberDTO;
//import com.realworld.project.common.config.SecurityConfig;
//import com.realworld.project.service.member.MemberService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//public class MemberServiceTest {
//    @InjectMocks
//    MemberService memberService;
//    @Mock
//    private SecurityConfig securityConfig;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//    @Test
//    @DisplayName("회원가입 테스트")
//    void saveMember() {
//        //given
//        MemberDTO member = MemberDTO.builder()
//                .userId("test8833")
//                .userEmail("hans983@naver.com")
//                .phoneNumber("01023599839")
//                .password("@Qwerty54953")
//                .build();
//
//        // when
//        long saveId = memberService.saveMember(member);
//
//
//
//    }
//
//}
