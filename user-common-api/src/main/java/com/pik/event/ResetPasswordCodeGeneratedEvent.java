package com.pik.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class ResetPasswordCodeGeneratedEvent {
    public final String id;
    public final String userId;
    public final String email;
    public final String token;
    public final Instant expirationDate;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ResetPasswordCodeGeneratedEvent(
            @JsonProperty("id") String id,
            @JsonProperty("userId") String userId,
            @JsonProperty("email") String email,
            @JsonProperty("token") String token,
            @JsonProperty("expirationDate") Instant expirationDate) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.token = token;
        this.expirationDate = expirationDate;
    }
}
