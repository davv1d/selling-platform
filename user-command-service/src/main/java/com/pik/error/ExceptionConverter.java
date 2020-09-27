package com.pik.error;

import org.springframework.http.ResponseEntity;

import java.util.function.BiFunction;

@FunctionalInterface
public interface ExceptionConverter<T> extends BiFunction<T, Throwable, ResponseEntity<?>> {
}
