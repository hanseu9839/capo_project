package com.realworld.feature.profile;

import com.realworld.global.code.SuccessCode;
import com.realworld.global.response.ApiResponse;
import com.realworld.feature.member.MemberDTO;
import com.realworld.feature.member.domain.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class ProfileController {
    private final ProfileCommandService postProfileUseCase;
    @PatchMapping("/member/nickname")
    public ResponseEntity<ApiResponse> nicknameUpdate(@AuthenticationPrincipal User user, @RequestBody MemberDTO memberDTO){
        log.info("userId {} ", user.getUsername());
        Member target = postProfileUseCase.nicknameUpdate(memberDTO, user.getUsername());
        return new ResponseEntity<>(ApiResponse.builder()
                .result(target)
                .resultCode(SuccessCode.UPDATE_SUCCESS.getStatus())
                .resultMsg(SuccessCode.UPDATE_SUCCESS.getMessage())
                .build(), HttpStatus.OK);
    }

}
