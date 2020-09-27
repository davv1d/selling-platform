package com.pik.controller;

import com.pik.dto.UserDto;
import com.pik.error.ExceptionConverter;
import com.pik.service.UserCommandSender;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class UserCommandController {
    private final UserCommandSender userCommandSender;
    private final ExceptionConverter<String> exceptionConverter;
    @Autowired
    EventStore eventStore;

    public UserCommandController(UserCommandSender userCommandSender, ExceptionConverter<String> exceptionConverter) {
        this.userCommandSender = userCommandSender;
        this.exceptionConverter = exceptionConverter;
    }

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

    @GetMapping("/events/{userId}")
    public List<Object> testEndpoint(@PathVariable String userId) {
        return this.eventStore.readEvents(userId).asStream()
                .map(domainEventMessage -> {
                    String payloadType = domainEventMessage.getPayloadType().getName();
                    Object payload = domainEventMessage.getPayload();
                    return payloadType;
                })
                .collect(Collectors.toList());
    }
}
