package com.realworld.feature.mail;

import com.realworld.feature.auth.AuthMailJpaEntity;
import org.springframework.stereotype.Component;

import com.realworld.feature.auth.AuthMail;

@Component
public class AuthMailMapper {
    public AuthMail toDomain(AuthMailJpaEntity entity){
        return AuthMail.builder()
                        .userEmail(entity.getUserEmail())
                        .authNumber(entity.getAuthNumber())
                        .registerDate(entity.getRegDt())
                        .build();
    }

    public AuthMailJpaEntity toEntity(AuthMail authMail){
        return AuthMailJpaEntity.builder()
                                .userEmail(authMail.getUserEmail())
                                .authNumber(authMail.getAuthNumber())
                                .regDt(authMail.getRegisterDate())
                                .build();
    }
}
