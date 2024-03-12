//package com.realworld.project.mail;
//
//import com.realworld.project.application.port.in.mail.GetMailUseCase;
//import com.realworld.project.application.port.out.mail.CommandAuthMailPort;
//import com.realworld.project.domain.AuthMail;
//import com.realworld.project.service.mail.AuthMailService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@SpringBootTest(properties = "spring.config.location =" + "classpath:/application.yml")
//class MailTest {
//    @Autowired
//    GetMailUseCase getMailUseCase;
//
//    @Autowired
//    AuthMailService authMailService;
//
//    @Autowired
//    CommandAuthMailPort commandAuthMailPort;
//
//    @Test
//    void sendMailCreateKeyCheck(){
//        // given
//        String userEmail = "hanseu9839@gmail.com";
//        String createKey = authMailService.createKey();
//        // when
//        Optional<AuthMail> authMail = commandAuthMailPort.saveEmailAuth(new AuthMail(userEmail, createKey));
//        // then
//        assertThat(authMail.get().getAuthNumber()).isEqualTo(createKey);
//    }
//
//    @Test
//    void sendMailEmailCheck(){
//        // given
//        String userEmail = "hanseu9839@gmail.com";
//        String createKey = authMailService.createKey();
//        // when
//        Optional<AuthMail> authMail = commandAuthMailPort.saveEmailAuth(new AuthMail(userEmail, createKey));
//        // then
//        assertThat(authMail.get().getUserEmail()).isEqualTo(userEmail);
//    }
//}
