package com.pik.controller;

import com.pik.domain.UserCredentials;
import com.pik.security.TokenGenerator;
import com.pik.security.repository.SecurityUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthenticateController {
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;
    private final SecurityUserRepository repository;

    @PostMapping("/")
    public ResponseEntity<?> authenticateUser(@RequestBody UserCredentials userCredentials) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userCredentials.getEmail(),
                        userCredentials.getPassword()
                ));
        List<String> auth = authenticate.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String jwtToken = tokenGenerator.generateJwtToken(repository.findByEmail(userCredentials.getEmail()).get(), auth);
        return ResponseEntity.ok(jwtToken);
    }

    @GetMapping("/test")
    public String test(@RequestHeader("user_id") String userId) {
        return "User with id " + userId;
    }
}
