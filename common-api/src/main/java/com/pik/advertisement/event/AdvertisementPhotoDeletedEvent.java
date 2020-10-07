package com.pik.advertisement.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AdvertisementPhotoDeletedEvent {
    public final String id;
    public final List<String> photosIds;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AdvertisementPhotoDeletedEvent(@JsonProperty("id") String id,
                                           @JsonProperty("photoIds") List<String> photosIds) {
        this.id = id;
        this.photosIds = photosIds;
    }
}
