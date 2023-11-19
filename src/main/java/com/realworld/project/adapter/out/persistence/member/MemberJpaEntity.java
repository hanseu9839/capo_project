package com.realworld.project.adapter.out.persistence.member;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Table(name="USER")
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Getter @Setter
@IdClass(MemberPK.class)
public class MemberJpaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    @Id
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
