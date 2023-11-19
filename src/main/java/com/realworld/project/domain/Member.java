package com.realworld.project.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Member {
    private Long userSeq;
    private String userId;
    private String phoneNumber;
    private String userEmail;
    private Date regDt;
    private Date createDt;
    private String delYn;

    @Builder
    public Member(Long userSeq, String userId, String phoneNumber, String userEmail, Date regDt, Date createDt, String delYn){
        this.userSeq = userSeq;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.userEmail = userEmail;
        this.regDt = regDt;
        this.createDt = createDt;
        this.delYn = delYn;
    }


}
