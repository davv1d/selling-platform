package com.pik.user.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ChangeUserPasswordCommand {
    @TargetAggregateIdentifier
    public final String userId;
    public final String password;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ChangeUserPasswordCommand(@JsonProperty("userId") String userId,
                                     @JsonProperty("password") String password) {
        this.userId = userId;
        this.password = password;
    }
}

