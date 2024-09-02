package com.realworld.global.config.handler;

import com.realworld.global.code.ErrorCode;
import com.realworld.global.config.exception.CustomJwtExceptionHandler;
import com.realworld.global.config.jwt.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        String id = null;
        if(accessor.getCommand() == StompCommand.CONNECT) {
            String token = resolveToken(accessor);

            try {
                if(token == null) throw new CustomJwtExceptionHandler(ErrorCode.JWT_UNKNOWN_ERROR);

                if (jwtTokenProvider.validateToken(token)) {
                    Authentication authentication = jwtTokenProvider.getAuthentication(token);
                    User user = (User) authentication.getPrincipal();
                    id = user.getUsername();
                }
            } catch (SecurityException | MalformedJwtException | IllegalArgumentException e) {
                throw new CustomJwtExceptionHandler(ErrorCode.JWT_WRONG_TYPE_TOKEN_ERROR);
            } catch (ExpiredJwtException e){
                throw new CustomJwtExceptionHandler(ErrorCode.JWT_TOKEN_EXPIRED_ERROR);
            } catch(UnsupportedJwtException e){
                throw new CustomJwtExceptionHandler(ErrorCode.UNSUPPORTED_TOKEN_ERROR);
            } catch (Exception e){
                throw new CustomJwtExceptionHandler(ErrorCode.JWT_UNKNOWN_ERROR);
            }

            accessor.addNativeHeader("senderUserId", id);
        }

        return message;
    }

    private String resolveToken(StompHeaderAccessor accessor) {
        String bearerToken = accessor.getFirstNativeHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
