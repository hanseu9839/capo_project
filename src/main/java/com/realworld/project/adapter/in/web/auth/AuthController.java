package com.realworld.project.adapter.in.web.auth;

import com.realworld.project.application.port.in.token.PostTokenUseCase;
import com.realworld.project.application.port.in.dto.TokenDTO;
import com.realworld.project.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final PostTokenUseCase postTokenUseCase;
    @PostMapping("/reissue")
    public ResponseEntity<ApiResponse> reissue(HttpServletRequest request, HttpServletResponse response){
        String accessToken = request.getHeader("AccessToken");
        String refreshToken = request.getHeader("RefreshToken");

        TokenDTO tokenDto = TokenDTO.builder()
                                    .accessToken(accessToken)
                                    .refreshToken(refreshToken)
                                    .build();

        return postTokenUseCase.reissue(tokenDto);
    }


}
