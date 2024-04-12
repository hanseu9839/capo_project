package com.realworld.feature.member.service;

import com.realworld.feature.member.domain.Member;
import com.realworld.feature.member.entity.MemberJpaEntity;
import com.realworld.feature.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository repository;


    @Override
    public Optional<Member> findByUserEmail(String userEmail) {
        MemberJpaEntity memberJpaEntity = MemberJpaEntity.builder()
                .userEmail(userEmail)
                .build();
        return Optional.ofNullable(repository.findByUserEmail(memberJpaEntity).toDomain());
    }

    @Override
    public Member getMemberByUserId(String userId) {
        return repository.findByUserId(userId).toDomain();
    }

    @Override
    public Optional<Member> findMemberByUserId(String userId) {
        return Optional.empty();
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
