package com.realworld.project.adapter.out.persistence.mail;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name="AUTH_MAIL")
@Entity
@NoArgsConstructor
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class AuthMailJpaEntity {
    @Id
    private String userEmail;

    private String authNumber;

    @LastModifiedDate
    private LocalDateTime regDt;

    @CreatedDate
    private LocalDateTime createDt;

    @Builder
    public AuthMailJpaEntity(String userEmail, String authNumber, LocalDateTime regDt, LocalDateTime createDt){
        this.userEmail = userEmail;
        this.authNumber = authNumber;
        this.regDt = regDt;
        this.createDt = createDt;
    }
}
