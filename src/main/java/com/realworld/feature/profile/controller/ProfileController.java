package com.realworld.feature.profile.controller;

import com.realworld.feature.profile.controller.request.UpdateNickNameRequest;
import com.realworld.feature.profile.service.ProfileCommandService;
import com.realworld.global.code.SuccessCode;
import com.realworld.global.response.ApiResponse;
import com.realworld.feature.member.domain.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class ProfileController {
    private final ProfileCommandService profileCommandService;
    @PatchMapping("/member/nickname")
    public ResponseEntity<ApiResponse<String>> nicknameUpdate(@AuthenticationPrincipal User user, @RequestBody UpdateNickNameRequest request){
        Member member = profileCommandService.updateNickname(request, user.getUsername());
        ApiResponse<String> updateApiResponse = new ApiResponse<>(member.getNickname(),
                SuccessCode.UPDATE_SUCCESS.getStatus(),
                SuccessCode.UPDATE_SUCCESS.getMessage());
        return ResponseEntity.ok(updateApiResponse);
    }
}
