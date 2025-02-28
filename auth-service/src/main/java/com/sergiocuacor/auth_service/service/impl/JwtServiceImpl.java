package com.sergiocuacor.auth_service.service.impl;

import com.sergiocuacor.auth_service.commons.dtos.TokenResponse;
import com.sergiocuacor.auth_service.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    private final String secretToken;

    public JwtServiceImpl(@Value("${jwt.secret}") String secretToken){
        this.secretToken=secretToken;
    }// Leemos el jwt secret del yaml

    @Override
    public TokenResponse generateToken(Long userId) {

        Date expirationDate = new Date(Long.MAX_VALUE);

        String token = Jwts.builder()
                .subject(String.valueOf(userId))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expirationDate)
                .signWith(getSigningKey())
                .compact();
        return TokenResponse.builder()
                .accessToken(token)
                .build();
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public boolean isExpired(String token) {
        try{
            return getClaims(token).getExpiration().before(new Date());
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Integer extractedUserId(String token) {
        try{
            return Integer.parseInt(getClaims(token).getSubject());
        }catch(Exception e){
            return null;
        }
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretToken);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
