package com.realworld.adapter.out.persistence.member;

import com.realworld.application.port.out.member.CommandMemberPort;
import com.realworld.application.port.out.member.LoadMemberPort;
import com.realworld.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
