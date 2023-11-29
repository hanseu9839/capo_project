package com.realworld.project.adapter.out.persistence.member;

import com.realworld.project.application.port.out.member.CommandMemberPort;
import com.realworld.project.application.port.out.member.LoadMemberPort;
import com.realworld.project.domain.Member;
import com.realworld.project.domain.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements CommandMemberPort, LoadMemberPort {
    private final MemberMapper memberMapper;
    private final MemberRepository repository;
    @Override
    public void saveMember(Member member) {
        MemberJpaEntity entity = memberMapper.toEntity(member);
        repository.save(entity);
    }

    @Override
    public Token login(Member member) {
        return null;
    }

    @Override
    public Member findByUserId(String userId) {
        Member member = memberMapper.toDomain(repository.findByUserId(userId));
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
