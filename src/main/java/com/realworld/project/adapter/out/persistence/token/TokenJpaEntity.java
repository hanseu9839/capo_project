package com.realworld.project.adapter.out.persistence.token;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Table(name="TOKEN")
@NoArgsConstructor
@Getter @Setter
@Entity
public class TokenJpaEntity implements Serializable {
    @Id
    @Column(name="user_id")
    private String userId;
    private String grantType;
    String accessToken;
    String refeshToken;

    @Builder
    public TokenJpaEntity(String userId,String grantType, String accessToken, String refeshToken){
        this.userId = userId;
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refeshToken = refeshToken;
    }

    public TokenJpaEntity updateToken(String accessToken, String refeshToken){
        this.accessToken = accessToken;
        this.refeshToken = refeshToken;
        return this;
    }
}
