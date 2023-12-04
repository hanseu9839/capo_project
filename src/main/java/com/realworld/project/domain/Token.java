package com.realworld.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class Token {
    private String userId;
    private String grantType;
    private String accessToken;
    private String refreshToken;
    @Builder
    Token(String userId, String grantType, String refreshToken, String accessToken){
        this.userId = userId;
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
