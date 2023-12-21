package com.realworld.project.application.port.in.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class AuthMailDTO {
    @JsonProperty("user_email")
    String userEmail;
    @JsonProperty("auth_number")
    String authNumber;
    @JsonProperty("reg_dt")
    LocalDateTime regDt;
    @JsonProperty("create_dt")
    LocalDateTime createDt;
    @Builder
    public AuthMailDTO(String userEmail, String authNumber){
        this.userEmail = userEmail;
        this.authNumber = authNumber;
    }

    @Builder
    public AuthMailDTO(String userEmail, String authNumber, LocalDateTime regDt, LocalDateTime createDt){
        this.userEmail = userEmail;
        this.authNumber = authNumber;
        this.regDt = regDt;
        this.createDt = createDt;
    }
}
