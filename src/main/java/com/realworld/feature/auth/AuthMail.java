package com.realworld.feature.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthMail {

    String userEmail;

    String authNumber;

    LocalDateTime registerDate;

    public boolean isEqualAuthNumber(String authNumber) {
        return authNumber.equals(this.authNumber);
    }

    public boolean isExpiredAuthMail(LocalDateTime regDt) {
        LocalDateTime nowDate = LocalDateTime.now();
        Duration diff = Duration.between(regDt.toLocalTime(), nowDate.toLocalTime());

        return diff.toMinutes() < 0 || diff.toMinutes() > 30;
    }
}
