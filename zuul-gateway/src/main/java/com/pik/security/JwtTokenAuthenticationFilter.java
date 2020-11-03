package com.pik.security;

import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenAuthenticationFilter.class);
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwtToken = jwtProvider.getToken(request);
            if (jwtToken != null && jwtProvider.validateJwtToken(jwtToken)) {
                String usernameFromJwtToken = jwtProvider.getUsernameFromJwtToken(jwtToken);
                List<SimpleGrantedAuthority> grantedAuthorities = jwtProvider.getGrantedAuthorityFromJwtToken(jwtToken);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usernameFromJwtToken, null, grantedAuthorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            LOGGER.error("Can not set user authentication -> Message: ", e);
        }
        filterChain.doFilter(request, response);
    }
}
