package com.realworld.project.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class AuthMail {
    String userEmail;
    String authNumber;
    LocalDateTime regDt;
    LocalDateTime createDt;
    @Builder
    public AuthMail(String userEmail,String authNumber) {
        this.userEmail = userEmail;
        this.authNumber = authNumber;
    }
    @Builder
    public AuthMail(String userEmail, String authNumber, LocalDateTime regDt, LocalDateTime createDt){
        this.userEmail = userEmail;
        this.authNumber = authNumber;
        this.regDt = regDt;
        this.createDt = createDt;
    }
}
