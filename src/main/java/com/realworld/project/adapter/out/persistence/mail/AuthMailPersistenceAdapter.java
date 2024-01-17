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
