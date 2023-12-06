package com.realworld.project.adapter.in.web.member;

import com.realworld.project.application.port.in.Member.PostMemberUseCase;
import com.realworld.project.application.port.in.Token.PostTokenUseCase;
import com.realworld.project.application.port.in.dto.MemberDTO;
import com.realworld.project.application.port.in.dto.TokenDTO;
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
    private final CommonApiResponse commonApiResponse;
    private final PostMemberUseCase postMemberUseCase;
    private final PostTokenUseCase postTokenUseCase;
    @PostMapping("/member")
    public ResponseEntity<?> memberRegister(@RequestBody MemberDTO memberDto){

        if(CommonUtil.isEmpty(memberDto)){

        }
        postMemberUseCase.saveMember(memberDto);
        return commonApiResponse.success(memberDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDTO memberDTO){
        TokenDTO tokenDto =postMemberUseCase.login(memberDTO);
        return commonApiResponse.success(tokenDto);
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody TokenDTO tokenDto){
        log.info("TokenDTO : {} ", tokenDto.getRefreshToken());

        return postTokenUseCase.reissue(tokenDto);
    }


}
