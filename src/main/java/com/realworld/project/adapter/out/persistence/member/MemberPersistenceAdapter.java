package com.realworld.project.adapter.out.persistence.member;

import com.realworld.project.application.port.out.member.CommandMemberPort;
import com.realworld.project.application.port.out.member.LoadMemberPort;
import com.realworld.project.common.Code.ErrorCode;
import com.realworld.project.common.Code.ResultErrorMsgCode;
import com.realworld.project.common.config.exception.CustomLoginExceptionHandler;
import com.realworld.project.common.utils.CommonUtil;
import com.realworld.project.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;
@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements CommandMemberPort, LoadMemberPort {
    private final MemberMapper memberMapper;
    private final MemberRepository repository;
    @Override
    public void saveMember(Member member) {
        if(existsByUserId(member.getUserId()) || existsByUserEmail(member.getUserEmail())){
            throw new CustomLoginExceptionHandler(ResultErrorMsgCode.LOGIN_DUPLICATION_ERROR.getMsg(),ErrorCode.LOGIN_DUPLICATION_ERROR);
        }
        MemberJpaEntity entity = memberMapper.toEntity(member);
        repository.save(entity);
    }


    @Override
    public Optional<Member> findByUserId(String userId) {
        Optional<Member> member = null;
        try{
            member = Optional.ofNullable(memberMapper.toDomain(repository.findByUserId(userId)));
        } catch (Exception e){
            log.info("CustomLoginException");
            throw new CustomLoginExceptionHandler(e.getMessage(),ErrorCode.LOGIN_REQUEST_ERROR);
        }

        return member;
    }

    @Override
    public boolean existsByUserEmail(String userEmail) {
        return repository.existsByUserEmail(userEmail);
    }

    @Override
    public boolean existsByUserId(String userId) {
        return repository.existsByUserId(userId);
    }

}
