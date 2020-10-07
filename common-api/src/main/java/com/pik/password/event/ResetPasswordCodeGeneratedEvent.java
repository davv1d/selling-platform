package com.pik.password.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResetPasswordCodeGeneratedEvent {
    public final String id;
    public final String userId;
    public final String email;
    public final String token;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ResetPasswordCodeGeneratedEvent(
            @JsonProperty("id") String id,
            @JsonProperty("userId") String userId,
            @JsonProperty("email") String email,
            @JsonProperty("token") String token) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.token = token;
    }
}
