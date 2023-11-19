package com.realworld.project.adapter.out.persistence.member;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name="USER")
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class MemberJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    private String userId;

    private String phoneNumber;

    private String userEmail;

    private String delYn;

    @LastModifiedDate
    private LocalDateTime regDt;

    @CreatedDate
    private LocalDateTime createDt;

    @Builder
    public MemberJpaEntity(Long userSeq, String userId, String phoneNumber, String userEmail, String delYn, LocalDateTime regDt, LocalDateTime createDt){
        this.userSeq = userSeq;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.userEmail = userEmail;
        this.delYn = delYn;
        this.regDt = regDt;
        this.createDt = createDt;
    }

    @Builder
    public MemberJpaEntity(String userId, String phoneNumber, String userEmail, String delYn, LocalDateTime regDt, LocalDateTime createDt){
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.userEmail = userEmail;
        this.delYn = delYn;
        this.regDt = regDt;
        this.createDt = createDt;
    }
}
