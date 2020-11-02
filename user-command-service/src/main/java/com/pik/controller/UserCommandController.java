package com.pik.controller;

import com.pik.dto.UserDto;
import com.pik.service.UserCommandSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserCommandController {
    private final UserCommandSender userCommandSender;

    @PostMapping("create")
    public CompletableFuture<String> createUser(@RequestBody UserDto userDto) {
        return this.userCommandSender.createUser(userDto);
    }

    @PostMapping("create/admin")
    public CompletableFuture<String> createAdmin(@RequestBody UserDto userDto) {
        return this.userCommandSender.createAdmin(userDto);
    }

    @PutMapping("deactivate/{userId}")
    public void deactivateUser(@PathVariable String userId) {
        this.userCommandSender.deactivateUser(userId);
    }

    @PutMapping("email/active/{userId}")
    public void activateByEmail(@PathVariable String userId) {
        this.userCommandSender.activateUser(userId);
    }
}
