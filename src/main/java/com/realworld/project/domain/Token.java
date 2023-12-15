package com.realworld.project.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude
public class Token {
    private String userId;
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
