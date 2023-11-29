package com.realworld.project.domain;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Member {
    private Long userSeq;
    private String userId;
    private String password;
    private String phoneNumber;
    private String userEmail;
    private LocalDateTime regDt;
    private LocalDateTime createDt;
    private String delYn;
    @Enumerated(EnumType.STRING)
    private Authority authority;
    @Builder
    public Member(Long userSeq, String userId, String password,String phoneNumber, String userEmail, LocalDateTime regDt, LocalDateTime createDt, String delYn, Authority authority){
        this.userSeq = userSeq;
        this.userId = userId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userEmail = userEmail;
        this.regDt = regDt;
        this.createDt = createDt;
        this.delYn = delYn;
        this.authority = authority;
    }

}
