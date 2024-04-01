package com.realworld.feature.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AuthMailDTO {
    @JsonProperty("user_email")
    String userEmail;

    @JsonProperty("auth_number")
    String authNumber;

    @JsonProperty("reg_dt")
    LocalDateTime regDt;
}
