package com.realworld.project.application.port.out.member;

import com.realworld.project.adapter.out.persistence.member.MemberJpaEntity;
import com.realworld.project.domain.Member;

public interface CommandMemberPort {
    void saveMember(Member member);
    void userRemove(MemberJpaEntity entity);

    void saveBackup(Member member);
}
