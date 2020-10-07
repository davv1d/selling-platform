package com.pik.user.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ActivateUserCommand {
    @TargetAggregateIdentifier
    public final String userId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ActivateUserCommand(@JsonProperty("userId") String userId) {
        this.userId = userId;
    }
}
