package com.pik.user.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDeactivatedEvent {
    public final String userId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UserDeactivatedEvent(@JsonProperty("userId") String userId) {
        this.userId = userId;
    }
}

