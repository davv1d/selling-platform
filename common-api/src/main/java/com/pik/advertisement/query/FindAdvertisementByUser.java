package com.pik.advertisement.query;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FindAdvertisementByUser {
    public final String userId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FindAdvertisementByUser(@JsonProperty("userId") String userId) {
        this.userId = userId;
    }
}
