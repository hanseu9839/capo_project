package com.realworld.project.adapter.out.persistence.member;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter @Setter
public class MemberPK implements Serializable {
    @Column(name="user_seq")
    private Long userSeq;
    @Column(name="user_id")
    private String userId;
    @Column(name="user_email")
    private String userEmail;
}
