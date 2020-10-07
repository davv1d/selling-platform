package com.pik.controller;

import com.pik.dto.UserDto;
import com.pik.dto.UsersListDto;
import com.pik.user.query.FindActivatedUserByEmail;
import com.pik.user.query.FindAllUsers;
import com.pik.user.query.FindUserByEmail;
import com.pik.user.query.FindUserById;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserQueryController {
    private final QueryGateway queryGateway;
    //for test
    @Autowired
    EventStore eventStore;

    @GetMapping("/get/{userId}")
    public CompletableFuture<UserDto> getUserById(@PathVariable String userId) {
        return this.queryGateway.query(new FindUserById(userId), ResponseTypes.instanceOf(UserDto.class));
    }

    @GetMapping("/get")
    public CompletableFuture<UsersListDto> getUsers() {
        return this.queryGateway.query(new FindAllUsers(), ResponseTypes.instanceOf(UsersListDto.class));
    }

    @GetMapping("/get/activate/email/{email}")
    public CompletableFuture<UserDto> getActivateUserByEmail(@PathVariable String email) {
        return this.queryGateway.query(new FindActivatedUserByEmail(email), ResponseTypes.instanceOf(UserDto.class));
    }

    @GetMapping("/get/email/{email}")
    public CompletableFuture<UserDto> getUserByEmail(@PathVariable String email) {
        return this.queryGateway.query(new FindUserByEmail(email), ResponseTypes.instanceOf(UserDto.class));
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
