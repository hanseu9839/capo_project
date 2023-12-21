package com.realworld.project.adapter.in.web.auth;

import com.realworld.project.application.port.in.token.PostTokenUseCase;
import com.realworld.project.application.port.in.dto.TokenDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/photocard/api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final PostTokenUseCase postTokenUseCase;
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody TokenDTO tokenDto){
        log.info("TokenDTO : {} ", tokenDto.getRefreshToken());

        return postTokenUseCase.reissue(tokenDto);
    }


}
