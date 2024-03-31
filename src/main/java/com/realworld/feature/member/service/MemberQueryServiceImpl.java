package com.realworld.feature.member.service;

import com.realworld.feature.member.domain.Member;
import com.realworld.feature.member.repository.LoadMemberPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberQueryServiceImpl implements MemberQueryService{

    private final LoadMemberPort loadMemberPort;

    @Override
    public Member findByUserEmail(String userEmail){
        return loadMemberPort.findByUserEmail(userEmail);
    }

    @Override
    public Optional<Member> getMemberByUserId(String userId) {
        return loadMemberPort.findByUserId(userId);
    }

    @Override
    public boolean existsByUserEmail(String userEmail) {
        return loadMemberPort.existsByUserEmail(userEmail);
    }

    @Override
    public boolean existsByUserId(String userId) {
        return loadMemberPort.existsByUserId(userId);
    }

}
