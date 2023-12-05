package com.realworld.project.application.port.in.Token;

import com.realworld.project.application.port.in.dto.TokenDTO;
import com.realworld.project.common.utils.response.CommonApiResponse;

public interface PostTokenUseCase {
    CommonApiResponse reissue(TokenDTO tokenDto);
}
