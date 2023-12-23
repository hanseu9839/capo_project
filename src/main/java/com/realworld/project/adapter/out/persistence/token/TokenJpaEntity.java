package com.realworld.project.adapter.out.persistence.token;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Table(name="token")
@NoArgsConstructor
@Getter @Setter
@DynamicUpdate
@Entity
public class TokenJpaEntity implements Serializable {
    @Id
    @Column(name="user_id")
    private String userId;
    private String grantType;
    String accessToken;
    String refreshToken;

    @Builder
    public TokenJpaEntity(String userId,String grantType, String accessToken, String refreshToken){
        this.userId = userId;
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public TokenJpaEntity updateToken(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        return this;
    }
}
