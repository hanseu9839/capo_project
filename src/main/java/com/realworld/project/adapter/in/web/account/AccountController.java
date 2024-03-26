package com.realworld.project.adapter.in.web.account;

import com.realworld.project.application.port.in.account.GetAccountUseCase;
import com.realworld.project.application.port.in.account.PostAccountUseCase;
import com.realworld.project.application.port.in.dto.MemberDTO;
import com.realworld.project.application.port.in.mail.GetMailUseCase;
import com.realworld.project.application.port.in.member.GetMemberUseCase;
import com.realworld.project.common.code.ErrorCode;
import com.realworld.project.common.code.SuccessCode;
import com.realworld.project.common.config.exception.CustomMailExceptionHandler;
import com.realworld.project.common.response.ApiResponse;
import com.realworld.project.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    @Autowired
    private final GetMemberUseCase getMemberUseCase;
    @Autowired
    private final PostAccountUseCase postAccountUseCase;
    @Autowired
    private final GetAccountUseCase getAccountUseCase;
    @Autowired
    private final GetMailUseCase getMailUseCase;
    @GetMapping("/member/account")
    public ResponseEntity<ApiResponse> account(@AuthenticationPrincipal User user){
        Member member = getAccountUseCase.getAccount(user.getUsername());

        return new ResponseEntity<>(ApiResponse.builder()
                .result(member)
                .resultCode(SuccessCode.SELECT_SUCCESS.getStatus())
                .resultMsg(SuccessCode.SELECT_SUCCESS.getMessage())
                .build(), HttpStatus.OK);
    }

    @PatchMapping("/member/password")
    public ResponseEntity<ApiResponse> passwordUpdate(@AuthenticationPrincipal User user,@RequestBody MemberDTO memberDto){
        postAccountUseCase.updatePassword(memberDto, user.getUsername());
        return new ResponseEntity<>(ApiResponse.builder()
                .resultCode(SuccessCode.UPDATE_SUCCESS.getStatus())
                .resultMsg(SuccessCode.UPDATE_SUCCESS.getMessage())
                .build(), HttpStatus.OK);
    }

    @PatchMapping("/member/email")
    public ResponseEntity<ApiResponse> emailUpdate(@AuthenticationPrincipal User user, @RequestBody MemberDTO memberDto){
        boolean exists=getMemberUseCase.existsByUserEmail(memberDto.getUserEmail());
        // 이메일 중복체크
        if(exists){
            throw new CustomMailExceptionHandler(ErrorCode.EMAIL_DUPLICATION_ERROR);
        }
        // 이메일 인증을 체크한다.
        getMailUseCase.emailAuthCheck(memberDto.getUserEmail(), memberDto.getAuthNumber());
        long updated = postAccountUseCase.updateEmail(user.getUsername(), memberDto);
        return new ResponseEntity<>(ApiResponse.builder()
                                                .resultCode(SuccessCode.UPDATE_SUCCESS.getStatus())
                                                .resultMsg(SuccessCode.UPDATE_SUCCESS.getMessage())
                                                .build(), HttpStatus.OK);
    }
}
