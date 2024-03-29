package com.realworld.feature.mail;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

import com.realworld.feature.auth.AuthMail;

public interface GetMailUseCase {
    AuthMail emailAuth(String userEmail) throws MessagingException, UnsupportedEncodingException;

    void emailAuthCheck(String userEmail,String authNumber);
}
