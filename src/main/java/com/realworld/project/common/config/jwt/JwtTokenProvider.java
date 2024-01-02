package com.realworld.project.common.config.jwt;

import com.realworld.project.application.port.in.dto.TokenDTO;
import com.realworld.project.domain.Token;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider{
    private static final String AUTHORITY_KEY = "auth";
    private static final String BEARER = "Bearer";


    private final Key key;

    // 30분
    private final long ACCESS_TOKEN_VALIDATION_SECOND =  30 * 60 * 1000;

    // 1일
    private final long REFRESH_TOKEN_VALIDATION_SECOND = 24 * 60 * 60 * 1000;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 사용자 정보를 기반으로 토큰을 생성하여 반환 해주는 메서드
     * @param authentication
     * @return TokenInfo : 토큰정보
     */
    public TokenDTO createToken(Authentication authentication){
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());
        long now = (new Date()).getTime();
        Date accessValidity = new Date(now + ACCESS_TOKEN_VALIDATION_SECOND);
        Date refreshValidity = new Date(now + REFRESH_TOKEN_VALIDATION_SECOND);
        log.info("accessValidity : {}", accessValidity);
        log.info("refreshValidity : {}", refreshValidity);
        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITY_KEY, authorities)
                .signWith(SignatureAlgorithm.HS256, key)
                .setExpiration(accessValidity)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                                    .setExpiration(refreshValidity)
                                    .signWith(SignatureAlgorithm.HS256, key)
                                    .compact();

        // TokenInfo 생성
        return TokenDTO.builder()
                        .grantType(BEARER)
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
    }

    /**
     * JWT 토큰을 복화하여 토큰에 들어있는 정보를 꺼내는 메서드
     * @param accessToken
     * @return
     */
    public Authentication getAuthentication(String accessToken){
        log.info("get Authentication : {}", accessToken);
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);
        log.info("auth"+claims.get("auth"));
        if(claims.get("auth") == null) {
            throw new RuntimeException("Token without credential.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(
                            claims.get(AUTHORITY_KEY).toString().split(","))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    /**
     * 유효한 토큰인지 확인
     * @param token
     * @return boolean 유효한 여부 반환
     */
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch(io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            log.info("Invalid JWT Token", e);
        } catch(ExpiredJwtException e){
            log.info("Expired JWT Token", e);
        } catch(UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch(IllegalArgumentException e){
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    /**
     *
     * @param accessToken
     * @return
     */
    private Claims parseClaims(String accessToken){
        log.info("parseClaims accessToken : {} ", accessToken);
        log.info("parseClaims key : {} ", key);
        try{
            return Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(accessToken)
                        .getBody();
        } catch(ExpiredJwtException e){
            return e.getClaims();
        }
    }


}
