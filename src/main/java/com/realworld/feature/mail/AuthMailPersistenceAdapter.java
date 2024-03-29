package com.realworld.feature.mail;

import com.realworld.feature.auth.AuthMail;

import com.realworld.feature.auth.AuthMailJpaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AuthMailPersistenceAdapter implements CommandAuthMailPort, LoadAuthMailPort {
    private final AuthMailMapper authMailMapper;
    private final AuthMailRepository authMailRepository;
    @Override
    public Optional<AuthMail> saveEmailAuth(AuthMail authMail) {
        log.info("authMail : {} ", authMail.getAuthNumber());
        log.info("authMail : {} ", authMail.getUserEmail());
        AuthMailJpaEntity entity = authMailMapper.toEntity(authMail);

        AuthMail target = authMailMapper.toDomain(authMailRepository.save(entity));
        return Optional.ofNullable(target);
    }


    @Override
    public Optional<AuthMailJpaEntity> findByUserEmail(String userEmail) {
        Optional<AuthMailJpaEntity> target = authMailRepository.findByUserEmail(userEmail);
        return target;
    }
}
