package com.realworld.project.adapter.out.persistence.member;

import com.realworld.project.application.port.out.member.CommandMemberPort;
import com.realworld.project.application.port.out.member.LoadMemberPort;
import com.realworld.project.domain.Member;
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
