package com.realworld.project.application.port.out.mail;

import com.realworld.project.adapter.out.persistence.mail.AuthMailJpaEntity;
import com.realworld.project.adapter.out.persistence.member.MemberJpaEntity;
import com.realworld.project.domain.AuthMail;

import java.util.Optional;

public interface CommandAuthMailPort {
    Optional<AuthMail> saveEmailAuth(AuthMail authMail);
}
