package com.realworld.project.adapter.in.web.member;

import com.realworld.project.application.port.in.member.GetMemberUseCase;
import com.realworld.project.application.port.in.member.PostMemberUseCase;
import com.realworld.project.application.port.in.dto.MemberDTO;
import com.realworld.project.application.port.in.token.PostTokenUseCase;
import com.realworld.project.common.code.SuccessCode;
import com.realworld.project.common.response.ApiResponse;
import com.realworld.project.domain.Member;
import com.realworld.project.domain.Token;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/photocard/api/v1")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final PostMemberUseCase postMemberUseCase;
    private final GetMemberUseCase getMemberUseCase;
    private final PostTokenUseCase postTokenUseCase;
    @PostMapping("/member")
    public ResponseEntity<ApiResponse> memberRegister(@RequestBody @Valid MemberDTO memberDto){
        postMemberUseCase.saveMember(memberDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .resultCode(SuccessCode.INSERT_SUCCESS.getStatus())
                .resultMsg(SuccessCode.INSERT_SUCCESS.getMessage())
                .build(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody MemberDTO memberDTO){
        Token token =postMemberUseCase.login(memberDTO);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(token)
                .resultCode(SuccessCode.SELECT_SUCCESS.getStatus())
                .resultMsg(SuccessCode.SELECT_SUCCESS.getMessage())
                .build(),HttpStatus.OK);
    }

    @PostMapping("/member/logout")
    public ResponseEntity<ApiResponse> logout(@AuthenticationPrincipal User user){
        log.info("userName : {} ", user.getUsername());
        //postTokenUseCase.deleteToken(token);
        postTokenUseCase.deleteToken(user.getUsername());
        return new ResponseEntity<>(ApiResponse.builder()
                .resultCode(200)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/duplication-check/user-id/{user_id}")
    public ResponseEntity<ApiResponse> userIdDuplicationCheck(@PathVariable("user_id") String userId, HttpServletResponse response){
        log.info("memberDTO : {} ", userId);
        boolean exists = getMemberUseCase.existsByUserId(userId);

        return new ResponseEntity<>(ApiResponse.builder()
                .result(exists)
                .resultCode(SuccessCode.SELECT_SUCCESS.getStatus())
                .resultMsg(SuccessCode.SELECT_SUCCESS.getMessage())
                .build(),HttpStatus.OK);
    }
}
