package com.realworld.project.adapter.out.persistence.mail;

import com.realworld.project.application.port.out.mail.CommandAuthMailPort;
import com.realworld.project.application.port.out.mail.LoadAuthMailPort;
import com.realworld.project.domain.AuthMail;
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
    public Optional<AuthMailJpaEntity> saveEmailAuth(AuthMail authMail) {
        log.info("authMail : {} ", authMail.getAuthNumber());
        log.info("authMail : {} ", authMail.getUserEmail());
        AuthMailJpaEntity entity = authMailMapper.toEntity(authMail);

        Optional<AuthMailJpaEntity> target = Optional.of(authMailRepository.save(entity));
        return target;
    }


    @Override
    public Optional<AuthMailJpaEntity> findByUserEmail(String userEmail) {
        Optional<AuthMailJpaEntity> target = authMailRepository.findByUserEmail(userEmail);
        return target;
    }
}
