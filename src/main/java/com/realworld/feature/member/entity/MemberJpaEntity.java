package com.realworld.feature.member.entity;

import com.realworld.feature.auth.Authority;
import com.realworld.feature.file.entity.FileJpaEntity;
import com.realworld.feature.like.entity.LikeJpaEntity;
import com.realworld.feature.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Entity
@Builder
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id"}))
@Slf4j
public class MemberJpaEntity {

    @Id
    @Column(name = "user_id")
    private String userId;

    private String password;

    private String phoneNumber;

    private String userEmail;

    private String delYn;

    private String nickname;

    private String content;

    private String profileImage;

    @LastModifiedDate
    private LocalDateTime regDt;

    @CreatedDate
    private LocalDateTime createDt;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(name = "oauth_image")
    private String oauthImage;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikeJpaEntity> likes;

    public Member toDomain() {
        return Member.builder()
                .password(getPassword())
                .userId(getUserId())
                .userEmail(getUserEmail())
                .phoneNumber(getPhoneNumber())
                .profileImage(getProfileImage())
                .nickname(getNickname())
                .content(getContent())
                .createDt(getCreateDt())
                .regDt(getRegDt())
                .delYn(getDelYn())
                .authority(getAuthority())
                .build();
    }

    public Member productToDomain() {
        return Member.builder()
                .userId(this.userId)
                .userEmail(this.userEmail)
                .nickname(this.nickname)
                .build();
    }
}
