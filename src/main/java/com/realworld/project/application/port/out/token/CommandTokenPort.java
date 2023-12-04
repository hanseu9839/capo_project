package com.realworld.project.application.port.out.token;

import com.realworld.project.domain.Token;

public interface CommandTokenPort {
    void saveToken(Token token);
}
