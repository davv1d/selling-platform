package com.pik.service;

import com.pik.dto.UserDto;

import java.util.concurrent.CompletableFuture;

public interface UserCommandSender {
    CompletableFuture<String> createUser(UserDto userDto);
    CompletableFuture<String> activateUser(String userId);
    CompletableFuture<String> deactivateUser(String userId);
}
