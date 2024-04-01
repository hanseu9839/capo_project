package com.realworld.feature.mail;

import com.realworld.feature.auth.AuthMail;
import com.realworld.feature.member.entity.MemberJpaEntity;

import java.util.Optional;

public interface CommandAuthMailPort {
    Optional<AuthMail> saveEmailAuth(AuthMail authMail);
}
