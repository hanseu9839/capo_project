package com.realworld.project.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Member {
    private Long userSeq;
    private String userId;
    private String phoneNumber;
    private String userEmail;
    private LocalDateTime regDt;
    private LocalDateTime createDt;
    private String delYn;

    @Builder
    public Member(Long userSeq, String userId, String phoneNumber, String userEmail, LocalDateTime regDt, LocalDateTime createDt, String delYn){
        this.userSeq = userSeq;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.userEmail = userEmail;
        this.regDt = regDt;
        this.createDt = createDt;
        this.delYn = delYn;
    }


}
