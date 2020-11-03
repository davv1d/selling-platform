package com.pik.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);


    public String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader(JwtProperties.STRING_HEADER);
        if (authHeader != null && authHeader.startsWith(JwtProperties.TOKEN_TYPE)) {
            return authHeader.replace(JwtProperties.TOKEN_TYPE, "");
        }
        return null;
    }

    public String getUsernameFromJwtToken(String token) {
        return getClaims(token).getSubject();
    }

    public String getUserIdFromJwtToken(String token) {
        Claims claims = getClaims(token);
        Object userId = claims.get("userId");
        return userId.toString();
    }


    @SuppressWarnings("unchecked")
    public List<SimpleGrantedAuthority> getGrantedAuthorityFromJwtToken(String token) {
        List<String> authorities = (List<String>) getClaims(token).get("authorities");
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(JwtProperties.SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JwtProperties.SECRET).parseClaimsJws(authToken);
            return true;
        } catch (ExpiredJwtException e) {
            LOGGER.error("Expired JWT token -> Message: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Unsupported JWT Token -> Message: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("Invalid JWT token -> Message: {}", e.getMessage());
        } catch (SignatureException e) {
            LOGGER.error("Invalid JWT signature -> Message: {} ", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("Jwt claims string is empty -> Message: {}", e.getMessage());
        }
        return false;
    }
}

