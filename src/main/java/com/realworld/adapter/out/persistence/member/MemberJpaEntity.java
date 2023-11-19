package com.realworld.adapter.out.persistence.member;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class MemberJpaEntity {

    @Id
    @Column(name="user_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    @Id
    @Column(name="user_id")
    private String userId;

    private String phoneNumber;

    private String userEmail;

    private String delYn;

    private Date regDt;

    private Date createDt;

    @Builder
    public MemberJpaEntity(Long userSeq, String userId, String phoneNumber, String userEmail, String delYn, Date regDt, Date createDt){
        this.userSeq = userSeq;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.userEmail = userEmail;
        this.delYn = delYn;
        this.regDt = regDt;
        this.createDt = createDt;
    }
}
