package com.realworld.project.application.port.in.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenDTO {
    @JsonProperty("user-id")
    private String userId;
    @JsonProperty("grant-type")
    private String grantType;
    @JsonProperty("access-token")
    private String accessToken;
    @JsonProperty("refresh-token")
    private String refreshToken;

    @Builder
    TokenDTO(String userId , String grantType, String accessToken, String refreshToken){
        this.userId = userId;
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
