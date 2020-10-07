package com.pik.user.query;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FindUserByEmail {
    public final String email;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FindUserByEmail(@JsonProperty("email") String email) {
        this.email = email;
    }
}
