package com.pik.password.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class GenerateResetPasswordCodeCommand {
    @TargetAggregateIdentifier
    public final String id;
    public final String userId;
    public final String email;
    public final String token;
    public final long secondsOfActivity;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public GenerateResetPasswordCodeCommand(
            @JsonProperty("id") String id,
            @JsonProperty("userId") String userId,
            @JsonProperty("email") String email,
            @JsonProperty("token") String token,
            @JsonProperty("activitySecondsTime") long secondsOfActivity) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.token = token;
        this.secondsOfActivity = secondsOfActivity;
    }
}
