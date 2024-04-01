package com.realworld.feature.loginout;

import com.realworld.feature.member.domain.Member;
import com.realworld.feature.member.service.MemberCommandService;
import com.realworld.feature.token.TokenQueryService;
import com.realworld.feature.token.Token;
import com.realworld.global.code.SuccessCode;
import com.realworld.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class LoginOutController {
    private final MemberCommandService memberCommandService;
    private final TokenQueryService tokenQueryService;

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Token>> login(@RequestBody LoginRequest request){
        Member member = Member.builder()
                .userId(request.getUserId())
                .password(request.getPassword())
                .build();

        Token token = memberCommandService.login(member);

        ApiResponse<Token> tokenApiResponse = new ApiResponse<>(token,
                SuccessCode.SELECT_SUCCESS.getStatus(), SuccessCode.SELECT_SUCCESS.getMessage());

        return ResponseEntity.ok(tokenApiResponse);
    }

    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(@AuthenticationPrincipal User user){
        tokenQueryService.deleteToken(user.getUsername());

        ApiResponse<Token> tokenApiResponse = new ApiResponse<>(null,
                200, "로그아웃 하였습니다.");

        return ResponseEntity.ok(tokenApiResponse);
    }
}
