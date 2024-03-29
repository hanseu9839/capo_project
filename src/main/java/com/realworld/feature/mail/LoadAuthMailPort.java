package com.realworld.feature.mail;

import com.realworld.feature.auth.AuthMailJpaEntity;

import java.util.Optional;

public interface LoadAuthMailPort {
    Optional<AuthMailJpaEntity> findByUserEmail(String userEmail);
}
