package com.realworld.project.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CreateJwtTest {
    @Value("$custom.jwt.secretkey}")
    private String secretKeyPlain;

    @Test
    void isExistSecretKey(){
        assertThat(secretKeyPlain).isNotNull();
    }
}
