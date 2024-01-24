package com.realworld.project.service.mail;

import com.realworld.project.adapter.out.persistence.mail.AuthMailJpaEntity;
import com.realworld.project.application.port.in.mail.GetMailUseCase;
import com.realworld.project.application.port.out.mail.CommandAuthMailPort;
import com.realworld.project.application.port.out.mail.LoadAuthMailPort;
import com.realworld.project.application.port.out.member.LoadMemberPort;
import com.realworld.project.common.code.ErrorCode;
import com.realworld.project.common.config.exception.CustomMailExceptionHandler;
import com.realworld.project.domain.AuthMail;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static com.realworld.project.common.code.ErrorCode.EMAIL_REQUEST_ERROR;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthMailService implements GetMailUseCase {
    private final JavaMailSender javaMailSender;
    private final CommandAuthMailPort commandAuthMailPort;
    private final LoadAuthMailPort loadAuthMailPort;
    private final LoadMemberPort loadMemberPort;
    @Value("${spring.mail.username}")
    private String from;
    @Override
    public AuthMail emailAuth(String userEmail) throws MessagingException, UnsupportedEncodingException {
        String authKey = createKey();

        MimeMessage mimeMessage = createMessage(userEmail, authKey);
        try{
            javaMailSender.send(mimeMessage);
        } catch(MailException es) { // 우선 구현 ==> 메일도 커스텀하여 사용
            es.printStackTrace();
            throw new IllegalArgumentException();
        }

        AuthMail authMail = AuthMail.builder()
                            .userEmail(userEmail)
                            .authNumber(authKey)
                            .build();
        Optional<AuthMail> target= commandAuthMailPort.saveEmailAuth(authMail);

        if(target.isPresent()){
            AuthMail authMailTarget = AuthMail.builder()
                                                .userEmail(target.get().getUserEmail())
                                                .authNumber(target.get().getAuthNumber())
                                                .regDt(target.get().getRegDt())
                                                .build();
            return authMailTarget;
        }  else {
            throw new CustomMailExceptionHandler(EMAIL_REQUEST_ERROR);
        }

    }

    @Transactional
    @Override
    public void emailAuthCheck(String userEmail, String authNumber) {

        // authMail에 있는 Mail정보 가져오기
        Optional<AuthMailJpaEntity> target = loadAuthMailPort.findByUserEmail(userEmail);

        if(target.isPresent()){
            expiredAuthEmailCheck(target.get().getRegDt());
            authNumberCheck(authNumber,target.get().getAuthNumber());
        } else{
            throw new CustomMailExceptionHandler(EMAIL_REQUEST_ERROR);
        }
    }

    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for(int i=0; i< 8; i++){
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch(index){
                case 0:
                    key.append((char)((int) (rnd.nextInt(26)) + 97));
                    // a~z (ex. 1+97 = 98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char)((int) (rnd.nextInt(26)) + 65));
                    // A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    break;
            }
        }

        return key.toString().toLowerCase();
    }

    public MimeMessage createMessage(String email, String authKey) throws UnsupportedEncodingException, MessagingException {
        // email 메시지 보내는 대상
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        mimeMessage.addRecipients(Message.RecipientType.TO, email);
        mimeMessage.setSubject("photocard 회원가입 이메일 인증");

        String msg = "";
        msg += "<div style='margin:100px;'>";
        msg += "<h1> 인증번호 : "+authKey+"</h1>";

        mimeMessage.setText(msg, "utf-8", "html");
        mimeMessage.setFrom(new InternetAddress(from,"PhotoCard_Admin"));

        return mimeMessage;
    }

    public void expiredAuthEmailCheck(LocalDateTime regDt){
        LocalDateTime nowDate = LocalDateTime.now();
        Duration diff = Duration.between(regDt.toLocalTime(), nowDate.toLocalTime());
        log.info("minutes diff : {}", diff.toMinutes());
        if(diff.toMinutes() < 0 || diff.toMinutes() > 30){
            throw new CustomMailExceptionHandler(ErrorCode.EMAIL_EXPIRED_ERROR);
        }
    }

    public void authNumberCheck(String authNumber,String target){
        if(!target.equals(authNumber)){
            throw new CustomMailExceptionHandler(ErrorCode.BAD_REQUEST_ERROR);
        }
    }

}
