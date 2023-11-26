package com.realworld.project.adapter.out.persistence.token;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table(name="TOKEN")
@NoArgsConstructor
@Entity
public class TokenEntity {
    @Id
    String userId;
    String accessToken;
    String refeshToken;

    @Builder
    public TokenEntity(String userId, String accessToken, String refeshToken){
        this.userId = userId;
        this.accessToken = accessToken;
        this.refeshToken = refeshToken;
    }

    public TokenEntity updateToken(String accessToken, String refeshToken){
        this.accessToken = accessToken;
        this.refeshToken = refeshToken;
        return this;
    }
}
