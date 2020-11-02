package com.pik.service;

import com.pik.dto.UserDto;

import java.util.concurrent.CompletableFuture;

public interface UserCommandSender {
    CompletableFuture<String> createUser(UserDto userDto);
    CompletableFuture<String> createAdmin(UserDto userDto);
    void activateUser(String userId);
    void deactivateUser(String userId);
}
