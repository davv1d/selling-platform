package com.pik.advertisement.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pik.advertisement.Photo;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

public class AddAdvertisementPhotosCommand {
    @TargetAggregateIdentifier
    public final String id;
    public final List<Photo> photos;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AddAdvertisementPhotosCommand(@JsonProperty("id") String id,
                                         @JsonProperty("photos") List<Photo> photos) {
        this.id = id;
        this.photos = photos;
    }
}
