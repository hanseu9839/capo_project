package com.realworld.project.login;

import com.realworld.feature.member.domain.Member;
import com.realworld.feature.member.repository.MemberRepository;
import com.realworld.feature.member.service.MemberQueryServiceImpl;
import com.realworld.global.code.ErrorCode;
import com.realworld.global.config.exception.CustomLoginExceptionHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoginTest {
    @Autowired
    MemberQueryServiceImpl memberQueryService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("아이디 없는 경우")
    void userNotFoundLoginFail() {

        // given
        Member member = Member.builder()
                .userId("test20931")
                .password("@qwer1234")
                .build();

        //when
        CustomLoginExceptionHandler exception = Assertions.assertThrows(CustomLoginExceptionHandler.class, () -> memberQueryService.getMemberByUserId(member.getUserId()).orElseThrow(() -> new CustomLoginExceptionHandler(ErrorCode.NOT_EXISTS_USERID))); // ID가 없는 경우


        //then
        assertEquals(ErrorCode.NOT_EXISTS_USERID.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("로그인 비밀번호 불일치")
    void inCorrectPassword() {
        //given
        Member member = Member.builder()
                .userId("admin12")
                .password("12")
                .build();

//        CustomLoginExceptionHandler exception = Assertions.assertThrows(CustomLoginExceptionHandler.class, () -> memberQueryService.loginAndGetToken(member));

//        assertEquals(ErrorCode.LOGIN_REQUEST_ERROR.getMessage(), exception.getMessage());
    }

}
