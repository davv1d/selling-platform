package com.pik.advertisement.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pik.advertisement.Photo;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CreateAdvertisementCommand {
    @TargetAggregateIdentifier
    public final String id;
    public final String userId;
    public final String categoryId;
    public final String title;
    public final String description;
    public final String location;
    public final int daysOfActivity;
    public final BigDecimal price;
    public final LocalDateTime timeOfAdd;
    public final String status;
    public final List<Photo> photos;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CreateAdvertisementCommand(@JsonProperty("id") String id,
                                      @JsonProperty("userId") String userId,
                                      @JsonProperty("categoryId") String categoryId,
                                      @JsonProperty("title") String title,
                                      @JsonProperty("description") String description,
                                      @JsonProperty("location") String location,
                                      @JsonProperty("daysOfActivity") int daysOfActivity,
                                      @JsonProperty("price") BigDecimal price,
                                      @JsonProperty("timeOfAdd") LocalDateTime timeOfAdd,
                                      @JsonProperty("status") String status,
                                      @JsonProperty("photos") List<Photo> photos) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.daysOfActivity = daysOfActivity;
        this.price = price;
        this.timeOfAdd = timeOfAdd;
        this.status = status;
        this.photos = photos;
    }
}
