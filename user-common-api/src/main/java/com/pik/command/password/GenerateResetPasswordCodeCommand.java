package com.pik.command.password;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.Instant;

public class GenerateResetPasswordCodeCommand {
    @TargetAggregateIdentifier
    public final String id;
    public final String userId;
    public final String email;
    public final String token;
    public final Instant expirationDate;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public GenerateResetPasswordCodeCommand(
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
