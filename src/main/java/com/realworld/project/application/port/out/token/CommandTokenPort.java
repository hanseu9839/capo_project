package com.realworld.project.application.port.out.token;

import com.realworld.project.adapter.out.persistence.token.TokenJpaEntity;
import com.realworld.project.domain.Token;

import java.util.Optional;

public interface CommandTokenPort {
    void saveToken(Token token);
}
