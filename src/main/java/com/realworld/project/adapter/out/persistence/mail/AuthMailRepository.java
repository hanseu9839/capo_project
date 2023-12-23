package com.realworld.project.adapter.out.persistence.mail;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthMailRepository extends JpaRepository<AuthMailJpaEntity, Long> {
    Optional<AuthMailJpaEntity> findByUserEmail(String userEmail);
}
