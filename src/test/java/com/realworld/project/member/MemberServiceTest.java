package com.realworld.project.member;


import com.realworld.feature.member.domain.Member;
import com.realworld.feature.member.service.MemberCommandServiceImpl;
import com.realworld.feature.member.service.MemberQueryService;
import com.realworld.global.code.ErrorCode;
import com.realworld.global.config.exception.CustomMemberExceptionHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    private MemberCommandServiceImpl memberCommandService;

    @Mock
    private MemberQueryService memberQueryService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 패스워드 타당성 체크")
    void signUpPasswordValidationCheck(){
        // given
        Member member = Member.builder()
                .userId("test11")
                .password("33")
                .phoneNumber("0103333333")
                .userEmail("test02@naver.com")
                .build();
        // when
        CustomMemberExceptionHandler exception = assertThrows(CustomMemberExceptionHandler.class, () -> memberCommandService.saveMember(member));

        // then
        assertEquals(ErrorCode.PASSWORD_REQUEST_ERROR.getMessage() , exception.getMessage());
    }

    @Test
    @DisplayName("회원가입 유저 아이디 타당성 체크")
    void signUpIdValidationCheck(){
        // given
        Member member = Member.builder()
                .userId("t")
                .password("@Qwer1234")
                .phoneNumber("0103333333")
                .userEmail("test02@naver.com")
                .build();

        // when
        CustomMemberExceptionHandler exception = assertThrows(CustomMemberExceptionHandler.class, () -> memberCommandService.saveMember(member));

        // then
        assertEquals(ErrorCode.VALIDATION_USERID_ERROR.getMessage(), exception.getMessage());
    }

//    @Test
//    @DisplayName("비밀번호 변경 시, 이메일 타당성 체크")
//    void emailValidationCheck(){
//        //given
//        Member member = Member.builder()
//                .userId("admin1234")
//                .password("@Qwer1234")
//                .phoneNumber("01034594987")
//                .build();
//
//        //when
//
//        //then
//    }
}
