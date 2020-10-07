package com.pik.user.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserChangePasswordEvent {
    public final String userId;
    public final String password;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UserChangePasswordEvent(@JsonProperty("userId") String userId,
                                   @JsonProperty("password") String password) {
        this.userId = userId;
        this.password = password;
    }
}
