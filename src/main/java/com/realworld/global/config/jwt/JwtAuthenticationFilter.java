package com.realworld.global.config.jwt;

import com.realworld.global.code.ErrorCode;
import com.realworld.global.config.exception.CustomJwtExceptionHandler;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String AUTHORZATION_HEADER = "Authorization";
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);
        try{
            if(token != null && jwtTokenProvider.validateToken(token)){
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
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

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORZATION_HEADER);

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
