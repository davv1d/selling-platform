package com.pik.advertisement.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AdvertisementExpiredEvent {
    public final String id;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AdvertisementExpiredEvent(@JsonProperty("id") String id) {
        this.id = id;
    }
}
