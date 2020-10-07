package com.pik.service;

import com.pik.dto.UserDto;
import com.pik.user.UserStatus;
import com.pik.user.command.ActivateUserCommand;
import com.pik.user.command.CreateUserCommand;
import com.pik.user.command.DeactivateUserCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class UserCommandSenderImpl implements UserCommandSender {
    private final CommandGateway commandGateway;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CompletableFuture<String> createUser(UserDto userDto) {
        String userId = UUID.randomUUID().toString();
        String status = UserStatus.INITIALIZE.toString();
        String password = this.passwordEncoder.encode(userDto.getPassword());
        return this.commandGateway.send(new CreateUserCommand(userId, userDto.getEmail(), password, status));
    }

    @Override
    public CompletableFuture<String> activateUser(String userId) {
        return this.commandGateway.send(new ActivateUserCommand(userId));
    }

    @Override
    public CompletableFuture<String> deactivateUser(String userId) {
        return this.commandGateway.send(new DeactivateUserCommand(userId));
    }
}
