package com.realworld.project.application.port.in.Token;

import com.realworld.project.application.port.in.dto.TokenDTO;
import com.realworld.project.common.utils.response.CommonApiResponse;
import org.springframework.http.ResponseEntity;

public interface PostTokenUseCase {
    ResponseEntity reissue(TokenDTO tokenDto);
}
