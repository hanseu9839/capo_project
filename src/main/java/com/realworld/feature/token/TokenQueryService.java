package com.realworld.feature.token;

import org.springframework.http.ResponseEntity;

public interface TokenQueryService {
    ResponseEntity reissue(TokenDTO tokenDto);

    void deleteToken(String userId);
}
