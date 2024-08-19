package com.realworld.global.config.handler;

import com.realworld.global.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        String id = null;
        if(accessor.getCommand() == StompCommand.CONNECT) {
            String accessToken = accessor.getFirstNativeHeader(HttpHeaders.AUTHORIZATION);
            if (jwtTokenProvider.validateToken(accessToken)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
                User user = (User) authentication.getPrincipal();
                id = user.getUsername();
            }
            accessor.addNativeHeader("senderUserId", id);
        }

        return message;
    }
}
