package com.makar.movielibrary.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {

    private final SecretKey secretKey;
    public JWTService(@Value("${JWT_SECRET}") String secretKey) {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ Duration.ofHours(10).toMillis()))
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token , UserDetails userDetails){
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && isTokenNotExpired(token));
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaim(String token , Function<Claims , T> claimsResolver){
        Claims claim = extractAllClaims(token);
        return claimsResolver.apply(claim);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenNotExpired(String token){
        return extractExpiration(token).after(new Date());
    }

}
