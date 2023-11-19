package com.realworld.adapter.in.web.member;

import com.realworld.application.port.in.PostMemberUseCase;
import com.realworld.common.utils.response.CommonUtil;
import com.realworld.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photocard/api/v1")
@RequiredArgsConstructor
public class MemberControllerV1 {

    private final PostMemberUseCase postMemberUseCase;

    @PostMapping("/member")
    public ResponseEntity memberRegister(@RequestBody Member member){
        if(CommonUtil.isEmpty(member)){

        }
        postMemberUseCase.saveMember(member);
        return (ResponseEntity) ResponseEntity.ok();
    }
}
