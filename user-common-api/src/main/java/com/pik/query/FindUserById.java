package com.pik.query;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FindUserById {
    public final String userId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FindUserById(@JsonProperty("userId") String userId) {
        this.userId = userId;
    }
}


