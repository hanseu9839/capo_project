package com.realworld.project.application.port.in.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class MemberDTO {
    @JsonProperty("user_seq")
    private Long userSeq;
    @JsonProperty("user_id")
    private String userId;
    private String password;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("user_email")
    private String userEmail;
    @JsonProperty("reg_dt")
    private LocalDateTime regDt;
    @JsonProperty("create_dt")
    private LocalDateTime createDt;
    @JsonProperty("del_yn")
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