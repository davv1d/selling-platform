package com.pik.controller;

import com.pik.dto.UserDto;
import com.pik.error.ExceptionConverter;
import com.pik.service.UserCommandSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
@RequiredArgsConstructor
public class UserCommandController {
    private final UserCommandSender userCommandSender;
    private final ExceptionConverter<String> exceptionConverter;

    @PostMapping("create")
    public CompletableFuture<ResponseEntity<?>> createUser(@RequestBody UserDto userDto) {
        return this.userCommandSender.createUser(userDto).handle(exceptionConverter);
    }

    @PutMapping("deactivate/{userId}")
    public CompletableFuture<ResponseEntity<?>> deactivateUser(@PathVariable String userId) {
        return this.userCommandSender.deactivateUser(userId).handle(exceptionConverter);

    }

    @PutMapping("email/active/{userId}")
    public CompletableFuture<ResponseEntity<?>> activateByEmail(@PathVariable String userId) {
        return this.userCommandSender.activateUser(userId).handle(exceptionConverter);
    }
}
