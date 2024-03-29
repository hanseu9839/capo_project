package com.realworld.feature.member.repository;

import com.realworld.feature.member.domain.Member;
import com.realworld.feature.member.entity.MemberJpaEntity;

public interface CommandMemberPort {
    MemberJpaEntity saveMember(Member member);
    void userRemove(Member member);
    long updatePassword(Member member);
    long updateEmail(Member member);
    void saveBackup(Member member);
    long nicknameUpdate(Member member);
}
