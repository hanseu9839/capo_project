package com.realworld.feature.token;

import com.realworld.feature.token.service.TokenCommandService;
import com.realworld.global.code.SuccessCode;
import com.realworld.global.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TokenController {

    private final TokenCommandService tokenCommandService;

    @PostMapping("/reissue")
    public ResponseEntity<ApiResponse<Token>> reissue(HttpServletRequest request){
        String accessToken = request.getHeader("AccessToken");
        String refreshToken = request.getHeader("RefreshToken");

        TokenDTO tokenDto = TokenDTO.builder()
                                    .accessToken(accessToken)
                                    .refreshToken(refreshToken)
                                    .build();


        Token token = tokenCommandService.reissue(tokenDto);

        ApiResponse<Token> tokenApiResponse = new ApiResponse<>(token,
                SuccessCode.UPDATE_SUCCESS.getStatus(), SuccessCode.UPDATE_SUCCESS.getMessage());

        return ResponseEntity.ok(tokenApiResponse);
    }


}
