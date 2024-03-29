package com.realworld.feature.member.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemberPK implements Serializable {
    private Long userSeq;
    private String userId;
}
