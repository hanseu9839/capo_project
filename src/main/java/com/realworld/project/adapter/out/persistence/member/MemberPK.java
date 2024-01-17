package com.realworld.project.adapter.out.persistence.member;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter @Setter
public class MemberPK implements Serializable {
    private Long userSeq;
    private String userId;
}
