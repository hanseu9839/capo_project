package com.realworld.feature.token.service;

import com.realworld.feature.token.Token;
import com.realworld.feature.token.TokenDTO;

public interface TokenCommandService {
    Token saveToken(Token token);

    Token reissue(TokenDTO tokenDto);

    void deleteToken(String userId);
}
