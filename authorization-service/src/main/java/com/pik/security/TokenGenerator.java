package com.pik.security;

import com.pik.domain.SecurityUser;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static com.pik.security.JwtProperties.EXPIRATION;
import static com.pik.security.JwtProperties.SECRET;

@Component
@RequiredArgsConstructor
public class TokenGenerator {
    public String generateJwtToken(SecurityUser user, List<String> auth) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("authorities", auth)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
}
