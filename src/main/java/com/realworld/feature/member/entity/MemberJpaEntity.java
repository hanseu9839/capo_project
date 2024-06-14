package com.realworld.feature.member.entity;

import com.realworld.feature.auth.Authority;
import com.realworld.feature.like.entity.LikeJpaEntity;
import com.realworld.feature.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id"}))
public class MemberJpaEntity {

    @Id
    @Column(name = "user_id")
    private String userId;

    private String password;

    private String phoneNumber;

    private String userEmail;

    private String delYn;

    private String nickname;

    @LastModifiedDate
    private LocalDateTime regDt;

    @CreatedDate
    private LocalDateTime createDt;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikeJpaEntity> likes;

    @Builder
    public MemberJpaEntity(String userId, String password, String phoneNumber, String userEmail, String delYn, LocalDateTime regDt, LocalDateTime createDt, Authority authority, String nickname) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.userEmail = userEmail;
        this.delYn = delYn;
        this.regDt = regDt;
        this.createDt = createDt;
        this.authority = authority;
        this.password = password;
        this.nickname = nickname;
    }

    @Builder
    public MemberJpaEntity(String userId, String password, String phoneNumber, String userEmail, String delYn, LocalDateTime regDt, LocalDateTime createDt, Authority authority) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.userEmail = userEmail;
        this.delYn = delYn;
        this.regDt = regDt;
        this.createDt = createDt;
        this.authority = authority;
        this.password = password;
    }

    @Builder
    public MemberJpaEntity(String userId, String phoneNumber, String userEmail, String delYn, LocalDateTime regDt, LocalDateTime createDt) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.userEmail = userEmail;
        this.delYn = delYn;
        this.regDt = regDt;
        this.createDt = createDt;
    }

    public Member toDomain() {
        return Member.builder()
                .password(getPassword())
                .userId(getUserId())
                .userEmail(getUserEmail())
                .phoneNumber(getPhoneNumber())
                .nickname(getNickname())
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
