package com.pik.user.query;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pik.user.UserStatus;

public class FindActivatedUserByEmail {
    public final String email;
    public final String status = UserStatus.ACTIVATE.toString();

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FindActivatedUserByEmail(@JsonProperty("email") String email) {
        this.email = email;
    }
}
