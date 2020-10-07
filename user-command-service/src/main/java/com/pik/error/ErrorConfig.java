package com.pik.error;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Configuration
public class ErrorConfig {

    @Bean
     public ExceptionConverter<String> httpErrorConverter() {
        return (t, throwable) -> {
            if (throwable == null) {
                return ResponseEntity.ok().build();
            } else {
                ApiError apiError = new ApiError(LocalDateTime.now(), throwable.getMessage());
                return ResponseEntity.badRequest().body(apiError);
            }
        };
    }
}
