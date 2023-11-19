package com.realworld.project.adapter.out.persistence.member;

import java.io.Serializable;
import java.util.Objects;

public class MemberPK implements Serializable {
    private Long userSeq;
    private String userId;
    public MemberPK(Long userSeq, String userId) {
        this.userSeq = userSeq;
        this.userId = userId;
    }

    public MemberPK() {
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        MemberPK pk = (MemberPK) o;
        return Objects.equals( userSeq, pk.userSeq ) &&
                Objects.equals( userId, pk.userId );
    }

    @Override
    public int hashCode() {
        return Objects.hash( userSeq, userId );
    }
}
