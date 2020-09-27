package com.pik.controller;

import com.pik.dto.UserDto;
import com.pik.dto.UsersListDto;
import com.pik.query.FindActivatedUserByEmail;
import com.pik.query.FindAllUsers;
import com.pik.query.FindUserByEmail;
import com.pik.query.FindUserById;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/")
public class UserQueryController {
    private final QueryGateway queryGateway;

    public UserQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

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
}
