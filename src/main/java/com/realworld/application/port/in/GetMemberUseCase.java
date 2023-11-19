package com.realworld.application.port.in;

import com.realworld.domain.Member;

import java.util.List;

public interface GetMemberUseCase {
    List<Member> getMember(List<Long> memberIds);
}
