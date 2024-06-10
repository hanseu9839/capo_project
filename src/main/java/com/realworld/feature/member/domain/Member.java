package com.realworld.feature.member.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.realworld.feature.auth.Authority;
import com.realworld.feature.member.entity.MemberJpaEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Member {
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

    public MemberJpaEntity toEntity() {
        return MemberJpaEntity.builder()
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

    public MemberJpaEntity productToEntity() {
        return MemberJpaEntity.builder()
                .userId(this.userId)
                .userEmail(this.userEmail)
                .nickname(this.nickname)
                .build();
    }
}
