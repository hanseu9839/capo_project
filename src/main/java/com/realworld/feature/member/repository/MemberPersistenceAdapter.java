package com.realworld.feature.member.repository;

import com.realworld.global.code.ErrorCode;
import com.realworld.global.code.ResultErrorMsgCode;
import com.realworld.global.config.exception.CustomLoginExceptionHandler;
import com.realworld.feature.auth.Authority;
import com.realworld.feature.member.domain.Member;
import com.realworld.feature.member.entity.BackUpMemberJpaEntity;
import com.realworld.feature.member.entity.MemberJpaEntity;

import com.realworld.global.config.exception.CustomMemberExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements CommandMemberPort, LoadMemberPort {

    private final MemberRepository repository;
    private final BackUpMemberRepository backRepository;

    @Override
    public MemberJpaEntity saveMember(Member member) {
        if(existsByUserId(member.getUserId()) || existsByUserEmail(member.getUserEmail())){
            throw new CustomLoginExceptionHandler(ResultErrorMsgCode.LOGIN_DUPLICATION_ERROR.getMsg(),ErrorCode.LOGIN_DUPLICATION_ERROR);
        }

        return repository.save(member.toEntity());
    }

    @Override
    public Optional<Member> findByUserId(String userId) {
        Optional<MemberJpaEntity> member = null;
        try{
            member = Optional.ofNullable(repository.findByUserId(userId));
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }

        return member.map(MemberJpaEntity::toDomain);
    }

    @Override
    public Member findByUserEmail(String userEmail) {
       MemberJpaEntity memberJpaEntity = MemberJpaEntity.builder()
               .userEmail(userEmail)
               .build();

        MemberJpaEntity target = repository.findByUserEmail(memberJpaEntity);

        return target.toDomain();
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
    public void userRemove(Member member) {
        repository.delete(member.toEntity());
    }

    @Override
    public long updatePassword(Member member) {
        long update = repository.updatePassword(member.toEntity());

        if(update < 0) throw new CustomLoginExceptionHandler(ErrorCode.FAIL_PASSWORD_CHANGE);

        return update;
    }

    @Override
    public long updateEmail(Member member) {
        return repository.updateEmail(member.toEntity());
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

    @Override
    public long updateNickname(Member member) {
        return repository.updateNickname(member.toEntity());
    }

}
