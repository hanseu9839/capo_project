package com.realworld.project.adapter.in.web.mail;

import com.realworld.project.application.port.in.dto.AuthMailDTO;
import com.realworld.project.application.port.in.mail.GetMailUseCase;
import com.realworld.project.common.code.SuccessCode;
import com.realworld.project.common.response.ApiResponse;
import com.realworld.project.domain.AuthMail;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Slf4j
@RestController
@RequestMapping("/photocard/api/v1")
@RequiredArgsConstructor
public class AuthMailController {
    private final GetMailUseCase getMailUseCase;
    @PostMapping("/auth/email")
    public ResponseEntity<ApiResponse> emailAuth(@RequestBody AuthMailDTO mailDto) throws MessagingException, UnsupportedEncodingException {
        log.info("mail : {} ", mailDto.getUserEmail());
        AuthMail authMail = getMailUseCase.emailAuth(mailDto.getUserEmail());
        return new ResponseEntity<>(ApiResponse.builder()
                .result(authMail)
                .resultCode(SuccessCode.INSERT_SUCCESS.getStatus())
                .resultMsg(SuccessCode.INSERT_SUCCESS.getMessage())
                .build(), HttpStatus.OK);
    }
}
