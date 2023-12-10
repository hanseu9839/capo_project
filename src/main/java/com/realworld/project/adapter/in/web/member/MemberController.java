package com.realworld.project.adapter.in.web.member;

import com.realworld.project.application.port.in.Member.GetMemberUseCase;
import com.realworld.project.application.port.in.Member.PostMemberUseCase;
import com.realworld.project.application.port.in.Token.PostTokenUseCase;
import com.realworld.project.application.port.in.dto.MemberDTO;
import com.realworld.project.application.port.in.dto.TokenDTO;
import com.realworld.project.common.Code.ResultMsgCode;
import com.realworld.project.common.Code.SuccessCode;
import com.realworld.project.common.response.ApiResponse;
import com.realworld.project.common.utils.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/photocard/api/v1")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final PostMemberUseCase postMemberUseCase;
    private final GetMemberUseCase getMemberUseCase;
    private final PostTokenUseCase postTokenUseCase;
    @PostMapping("/member")
    public ResponseEntity<?> memberRegister(@RequestBody MemberDTO memberDto){
        postMemberUseCase.saveMember(memberDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result_code(SuccessCode.INSERT_SUCCESS.getStatus())
                .result_msg(SuccessCode.INSERT_SUCCESS.getMessage())
                .build(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDTO memberDTO){
        TokenDTO tokenDto =postMemberUseCase.login(memberDTO);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(tokenDto)
                .result_code(SuccessCode.SELECT_SUCCESS.getStatus())
                .result_msg(SuccessCode.SELECT_SUCCESS.getMessage())
                .build(),HttpStatus.OK);
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody TokenDTO tokenDto){
        log.info("TokenDTO : {} ", tokenDto.getRefreshToken());

        return postTokenUseCase.reissue(tokenDto);
    }

    @GetMapping("/duplication-check/user-id")
    public ResponseEntity<?> userIdDuplicationCheck(@RequestBody MemberDTO memberDTO){
        log.info("memberDTO : {} ", memberDTO.getUserId());
        boolean exists = getMemberUseCase.existsByUserId(memberDTO.getUserId());

        return new ResponseEntity<>(ApiResponse.builder()
                .result(exists)
                .result_code(SuccessCode.SELECT_SUCCESS.getStatus())
                .result_msg(SuccessCode.SELECT_SUCCESS.getMessage())
                .build(),HttpStatus.OK);
    }

}
