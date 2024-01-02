package com.realworld.project.application.port.in.account;

import com.realworld.project.domain.Member;

public interface GetAccountUseCase {
    Member getAccount(String userId);
}
