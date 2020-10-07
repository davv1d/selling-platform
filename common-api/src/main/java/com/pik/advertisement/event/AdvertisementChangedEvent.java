package com.pik.advertisement.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

public class AdvertisementChangedEvent {
    @TargetAggregateIdentifier
    public final String id;
    public final String title;
    public final String description;
    public final BigDecimal price;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AdvertisementChangedEvent(@JsonProperty("id") String id,
                                     @JsonProperty("title") String title,
                                     @JsonProperty("description") String description,
                                     @JsonProperty("price") BigDecimal price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
