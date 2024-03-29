package com.realworld.feature.mail;

import com.realworld.global.code.ErrorCode;
import com.realworld.global.code.SuccessCode;
import com.realworld.global.config.exception.CustomMailExceptionHandler;
import com.realworld.global.response.ApiResponse;
import com.realworld.feature.auth.AuthMailDTO;
import com.realworld.feature.member.service.MemberQueryService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth/email")
@RequiredArgsConstructor
public class AuthMailController {
    private final GetMailUseCase getMailUseCase;
    private final MemberQueryService getMemberUseCase;

    @PostMapping("")
    public ResponseEntity<ApiResponse> emailAuth(@RequestBody AuthMailDTO mailDto) throws MessagingException, UnsupportedEncodingException {
        log.info("mail : {} ", mailDto.getUserEmail());
        getMailUseCase.emailAuth(mailDto.getUserEmail());
        return new ResponseEntity<>(ApiResponse.builder()
                .resultCode(SuccessCode.INSERT_SUCCESS.getStatus())
                .build(), HttpStatus.CREATED);
    }

    @GetMapping("/{auth_number}")
    public ResponseEntity<ApiResponse> emailAuthNumber(@RequestParam("user_email") String userEmail, @PathVariable("auth_number") String authNumber){
        boolean exists=getMemberUseCase.existsByUserEmail(userEmail);
        // 이메일 중복체크
        if(exists){
            throw new CustomMailExceptionHandler(ErrorCode.EMAIL_DUPLICATION_ERROR);
        }
        getMailUseCase.emailAuthCheck(userEmail, authNumber);
        return new ResponseEntity<>(ApiResponse.builder()
                .resultCode(200)
                .build(), HttpStatus.OK);
    }
}
