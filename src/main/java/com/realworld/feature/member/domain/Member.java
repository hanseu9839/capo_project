package com.realworld.feature.member.domain;

import com.realworld.feature.auth.Authority;

import com.realworld.feature.member.entity.MemberJpaEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class Member {
    private Long userSeq;

    private String userId;

    private String password;

    private String currentPassword;
    private String newPassword;

    private String phoneNumber;

    private String userEmail;

    private LocalDateTime regDt;

    private LocalDateTime createDt;

    private String delYn;

    private Authority authority;

    private String nickname;

    public MemberJpaEntity toEntity(){
        return MemberJpaEntity.builder()
                .userSeq(getUserSeq())
                .userId(getUserId())
                .userEmail(getUserEmail())
                .password(getPassword())
                .phoneNumber(getPhoneNumber())
                .nickname(getNickname())
                .createDt(getCreateDt())
                .regDt(getRegDt())
                .delYn(getDelYn())
                .authority(getAuthority())
                .build();
    }
}
