package com.realworld.feature.member.controller;

import com.realworld.feature.mail.GetMailUseCase;
import com.realworld.feature.member.controller.request.FindPasswordRequest;
import com.realworld.feature.member.service.MemberCommandService;
import com.realworld.feature.member.service.MemberQueryService;
import com.realworld.feature.member.domain.Member;
import com.realworld.global.code.SuccessCode;
import com.realworld.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    // TODO: Member로 이동하는 게 좋을 것 같음..

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final GetMailUseCase getMailUseCase;

    /**
     * 비밀번호 찾기
     */
    @PatchMapping("/find-password")
    public ResponseEntity<ApiResponse> findPassword(@RequestBody FindPasswordRequest request){
        // 이메일 인증. 이메일로 비밀번호 찾음.
        getMailUseCase.emailAuthCheck(request.getUserEmail(), request.getAuthNumber());

        Member member = Member.builder()
                .userEmail(request.getUserEmail())
                .newPassword(request.getNewPassword())
                .build();

        memberCommandService.updatePassword(member);

        ApiResponse<?> memberApiResponse = new ApiResponse<>(null,
                SuccessCode.UPDATE_SUCCESS.getStatus(), SuccessCode.UPDATE_SUCCESS.getMessage());

        return ResponseEntity.ok(memberApiResponse);
    }

    /**
     * 아이디 찾기
     * @param userEmail
     * @param authNumber
     * @return
     */
    @GetMapping("/find-userId/{auth_number}")
    public ResponseEntity<ApiResponse<String>> findUserId(@RequestParam("user_email") String userEmail, @PathVariable("auth_number")String authNumber){
        // 이메일 인증으로 아이디 찾기
        getMailUseCase.emailAuthCheck(userEmail, authNumber);

        Member member = memberQueryService.findByUserEmail(userEmail);

        ApiResponse<String> memberApiResponse = new ApiResponse<>(member.getUserId(),
                SuccessCode.SELECT_SUCCESS.getStatus(), SuccessCode.SELECT_SUCCESS.getMessage());

        return ResponseEntity.ok(memberApiResponse);
    }

}
