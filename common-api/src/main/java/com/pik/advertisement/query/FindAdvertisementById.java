package com.pik.advertisement.query;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FindAdvertisementById {
    public final String id;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FindAdvertisementById(@JsonProperty("id") String id) {
        this.id = id;
    }
}
