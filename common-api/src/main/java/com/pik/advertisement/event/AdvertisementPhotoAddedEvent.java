package com.pik.advertisement.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pik.advertisement.Photo;

import java.util.List;

public class AdvertisementPhotoAddedEvent {
    public final String id;
    public final List<Photo> photos;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AdvertisementPhotoAddedEvent(@JsonProperty("id") String id,
                                        @JsonProperty("photos") List<Photo> photos) {
        // TODO: 07.10.2020 Photo{id..} error in equals methods. Not expected "photo"
        this.id = id;
        this.photos = photos;
    }
}
