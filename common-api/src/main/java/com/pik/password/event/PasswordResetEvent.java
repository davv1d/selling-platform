package com.pik.password.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PasswordResetEvent {
    public final String id;
    public final String userId;
    public final String newPassword;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PasswordResetEvent(@JsonProperty("id") String id,
                              @JsonProperty("userId") String userId,
                              @JsonProperty("newPassword") String newPassword) {
        this.id = id;
        this.userId = userId;
        this.newPassword = newPassword;
    }
}
