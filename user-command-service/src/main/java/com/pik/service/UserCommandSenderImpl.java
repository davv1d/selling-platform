package com.pik.service;

import com.pik.aggregate.UserStatus;
import com.pik.command.ActivateUserCommand;
import com.pik.command.CreateUserCommand;
import com.pik.command.DeactivateUserCommand;
import com.pik.dto.UserDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class UserCommandSenderImpl implements UserCommandSender {
    private final CommandGateway commandGateway;
    private final PasswordEncoder passwordEncoder;

    public UserCommandSenderImpl(CommandGateway commandGateway, PasswordEncoder passwordEncoder) {
        this.commandGateway = commandGateway;
        this.passwordEncoder = passwordEncoder;
    }

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
