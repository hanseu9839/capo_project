package com.realworld.project.application.port.in.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[a-z0-9]{6,12}$",
             message = "아이디 형식이 잘못되었습니다.")
    private String userId;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,16}$",
             message = "올바르지 않는 비밀번호입니다.")
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
    private String nickname;
    @JsonProperty("current_password")
    private String currentPassword;
    @JsonProperty("new_password")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,16}$",
            message = "올바르지 않는 비밀번호입니다.")
    private String newPassword;

    @Builder
    public MemberDTO(Long userSeq, String userId, String password,String phoneNumber, String userEmail, LocalDateTime regDt, LocalDateTime createDt, String delYn, String nickname, String currentPassword, String newPassword){
        this.userSeq = userSeq;
        this.userId = userId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userEmail = userEmail;
        this.regDt = regDt;
        this.createDt = createDt;
        this.delYn = delYn;
        this.nickname = nickname;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }


    @Builder
    public MemberDTO(Long userSeq, String userId, String password,String phoneNumber, String userEmail, LocalDateTime regDt, LocalDateTime createDt, String delYn, String nickname){
        this.userSeq = userSeq;
        this.userId = userId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userEmail = userEmail;
        this.regDt = regDt;
        this.createDt = createDt;
        this.delYn = delYn;
        this.nickname = nickname;
    }

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