package com.realworld.project.adapter.in.web.member;

import com.realworld.project.application.port.in.Member.GetMemberUseCase;
import com.realworld.project.application.port.in.Member.PostMemberUseCase;
import com.realworld.project.application.port.in.Token.PostTokenUseCase;
import com.realworld.project.application.port.in.dto.MemberDTO;
import com.realworld.project.application.port.in.dto.TokenDTO;
import com.realworld.project.common.config.jwt.JwtTokenProvider;
import com.realworld.project.common.utils.response.CommonApiResponse;
import com.realworld.project.common.utils.response.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/photocard/api/v1")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final PostMemberUseCase postMemberUseCase;
    private final PostTokenUseCase postTokenUseCase;
    @PostMapping("/member")
    public CommonApiResponse memberRegister(@RequestBody MemberDTO memberDto){

        if(CommonUtil.isEmpty(memberDto)){

        }
        postMemberUseCase.saveMember(memberDto);
        return CommonApiResponse.createSuccessWithNoContent();
    }

    @PostMapping("/login")
    public CommonApiResponse login(@RequestBody MemberDTO memberDTO){
        TokenDTO tokenDto =postMemberUseCase.login(memberDTO);
        return CommonApiResponse.createSuccess(tokenDto);
    }

    @PostMapping("/reissue")
    public CommonApiResponse reissue(@RequestBody TokenDTO tokenDto){
        log.info("TokenDTO : {} ", tokenDto.getRefreshToken());
        postTokenUseCase.reissue(tokenDto);

        return CommonApiResponse.createSuccessWithNoContent();
    }
}
