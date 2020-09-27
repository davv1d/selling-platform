package com.pik.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCreatedEvent {
    public final String userId;
    public final String email;
    public final String password;
    public final String status;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UserCreatedEvent(@JsonProperty("userId") String userId,
                            @JsonProperty("email") String email,
                            @JsonProperty("password") String password,
                            @JsonProperty("status") String status) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.status = status;
    }
}
