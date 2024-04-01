package com.realworld.feature.mail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realworld.feature.auth.AuthMailJpaEntity;

import java.util.Optional;

@Repository
public interface AuthMailRepository extends JpaRepository<AuthMailJpaEntity, Long> {
    Optional<AuthMailJpaEntity> findByUserEmail(String userEmail);
}
