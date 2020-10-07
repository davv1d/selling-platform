package com.pik.advertisement.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

public class DeleteAdvertisementPhotoCommand {
    @TargetAggregateIdentifier
    public final String id;
    public final List<String> photosIds;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public DeleteAdvertisementPhotoCommand(@JsonProperty("id") String id,
                                           @JsonProperty("photoId") List<String> photosIds) {
        this.id = id;
        this.photosIds = photosIds;
    }
}
