package com.realworld.project.adapter.in.web.member;

import com.realworld.project.adapter.out.persistence.member.MemberJpaEntity;
import com.realworld.project.application.port.in.mail.GetMailUseCase;
import com.realworld.project.application.port.in.member.GetMemberUseCase;
import com.realworld.project.application.port.in.member.PostMemberUseCase;
import com.realworld.project.application.port.in.dto.MemberDTO;
import com.realworld.project.application.port.in.token.PostTokenUseCase;
import com.realworld.project.common.code.ErrorCode;
import com.realworld.project.common.code.SuccessCode;
import com.realworld.project.common.config.exception.CustomMemberExceptionHandler;
import com.realworld.project.common.response.ApiResponse;
import com.realworld.project.domain.Member;
import com.realworld.project.domain.Token;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/photocard/api/v1")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final PostMemberUseCase postMemberUseCase;
    private final GetMemberUseCase getMemberUseCase;
    private final PostTokenUseCase postTokenUseCase;
    private final GetMailUseCase getMailUseCase;
    private final PasswordEncoder passwordEncoder;
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

    @PostMapping("/member/out")
    public ResponseEntity<ApiResponse> userRemove(@AuthenticationPrincipal User user, @RequestBody MemberDTO memberDto){
        Optional<MemberJpaEntity> targetMember = getMemberUseCase.findByUserId(user.getUsername());

        Member member = Member.builder()
                                .userId(targetMember.get().getUserId())
                                .authority(targetMember.get().getAuthority())
                                .phoneNumber(targetMember.get().getPhoneNumber())
                                .userEmail(targetMember.get().getUserEmail())
                                .password(targetMember.get().getPassword())
                                .nickname(targetMember.get().getNickname())
                                .phoneNumber(targetMember.get().getPhoneNumber())
                                .build();

        if(passwordEncoder.matches(memberDto.getPassword(), member.getPassword())){
            postMemberUseCase.remove(targetMember.get());
            postMemberUseCase.saveBackupMember(member);
        } else{
            throw new CustomMemberExceptionHandler(ErrorCode.VALIDATION_PASSWORD_ERROR);
        }

        return new ResponseEntity<>(ApiResponse.builder()
                                                .resultMsg(SuccessCode.DELETE_SUCCESS.getMessage())
                                                .resultCode(SuccessCode.DELETE_SUCCESS.getStatus())
                                                .build(),HttpStatus.OK);
    }

    @PatchMapping("/user/find-password/{auth_number}")
    public ResponseEntity<ApiResponse> findPassword(@AuthenticationPrincipal User user,@RequestBody MemberDTO memberDto, @PathVariable("auth_number") String authNumber){
        Optional<MemberJpaEntity> targetMember = getMemberUseCase.findByUserId(user.getUsername());
        getMailUseCase.emailAuthCheck(targetMember.get().getUserEmail(), authNumber);

        return new ResponseEntity<>(ApiResponse.builder()
                                                .resultMsg(SuccessCode.UPDATE_SUCCESS.getMessage())
                                                .resultCode(SuccessCode.UPDATE_SUCCESS.getStatus())
                                                .build(),HttpStatus.OK);
    }

    @PatchMapping("/user/find-userId/{auth_number}")
    public ResponseEntity<ApiResponse> findUserId(@RequestBody MemberDTO memberDto){
        Optional<MemberJpaEntity> targetMember = getMemberUseCase.findByUserId(memberDto.getUserId());


        return new ResponseEntity<>(ApiResponse.builder()
                                               .resultMsg(SuccessCode.UPDATE_SUCCESS.getMessage())
                                               .resultCode(SuccessCode.UPDATE_SUCCESS.getStatus())
                                               .build(), HttpStatus.OK);
    }

}
