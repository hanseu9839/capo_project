package com.realworld.project.application.port.in.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDTO {
    private Long userSeq;
    private String userId;
    private String password;
    private String phoneNumber;
    private String userEmail;
    private LocalDateTime regDt;
    private LocalDateTime createDt;
    private String delYn;

    @Builder
    public MemberDTO(Long userSeq, String userId, String password,String phoneNumber, String userEmail, LocalDateTime regDt, LocalDateTime createDt, String delYn){
        this.userSeq = userSeq;
        this.userId = userId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userEmail = userEmail;
        this.regDt = regDt;
        this.createDt = createDt;
        this.delYn = delYn;
    }

}