package com.realworld.project.application.port.in.token;

import com.realworld.project.application.port.in.dto.TokenDTO;
import org.springframework.http.ResponseEntity;

public interface PostTokenUseCase {
    ResponseEntity reissue(TokenDTO tokenDto);

    void deleteToken(String userId);
}
