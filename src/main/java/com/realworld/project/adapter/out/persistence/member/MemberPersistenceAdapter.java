package com.realworld.project.adapter.out.persistence.member;

import com.realworld.project.application.port.in.dto.MemberDTO;
import com.realworld.project.application.port.out.member.CommandMemberPort;
import com.realworld.project.application.port.out.member.LoadMemberPort;
import com.realworld.project.common.code.ErrorCode;
import com.realworld.project.common.code.ResultErrorMsgCode;
import com.realworld.project.common.config.exception.CustomLoginExceptionHandler;
import com.realworld.project.domain.Authority;
import com.realworld.project.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements CommandMemberPort, LoadMemberPort {
    private final MemberMapper memberMapper;
    private final MemberRepository repository;
    private final BackUpMemberRepository backRepository;
    @Override
    public void saveMember(Member member) {
        if(existsByUserId(member.getUserId()) || existsByUserEmail(member.getUserEmail())){
            throw new CustomLoginExceptionHandler(ResultErrorMsgCode.LOGIN_DUPLICATION_ERROR.getMsg(),ErrorCode.LOGIN_DUPLICATION_ERROR);
        }
        MemberJpaEntity entity = memberMapper.toEntity(member);
        repository.save(entity);
    }



    @Override
    public Optional<MemberJpaEntity> findByUserId(String userId) {
        Optional<MemberJpaEntity> member = null;
        try{
            member = Optional.ofNullable(repository.findByUserId(userId));
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }

        return member;
    }


    @Override
    public Optional<MemberJpaEntity> findByUserEmail(String userEmail) {
       Optional<MemberJpaEntity> member= null;
        try{
            member = repository.findByUserEmail(userEmail);
        } catch(Exception e){
            e.printStackTrace();
            throw e;
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

    @Override
    public void userRemove(MemberJpaEntity entity) {
        repository.delete(entity);
    }

    @Override
    public long updatePassword(Member member) {
        MemberJpaEntity memberJpaEntity = memberMapper.toEntity(member);
        long update = repository.updatePassword(memberJpaEntity);
        return update;
    }

    @Override
    public void saveBackup(Member member) {
        BackUpMemberJpaEntity entity = BackUpMemberJpaEntity.builder()
                                                            .authority(Authority.ROLE_USER)
                                                            .userId(member.getUserId())
                                                            .phoneNumber(member.getPhoneNumber())
                                                            .userEmail(member.getUserEmail())
                                                            .delYn("N")
                                                            .password(member.getPassword())
                                                            .nickname(member.getNickname())
                                                            .createDt(member.getCreateDt())
                                                            .regDt(member.getRegDt())
                                                            .build();
        backRepository.save(entity);
    }

}
