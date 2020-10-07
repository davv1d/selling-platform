package com.pik.aggregate;

import com.pik.advertisement.AdvertisementStatus;
import com.pik.advertisement.Photo;
import com.pik.advertisement.command.AddAdvertisementPhotosCommand;
import com.pik.advertisement.command.ChangeAdvertisementCommand;
import com.pik.advertisement.command.CreateAdvertisementCommand;
import com.pik.advertisement.command.DeleteAdvertisementPhotoCommand;
import com.pik.advertisement.event.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.deadline.DeadlineManager;
import org.axonframework.deadline.annotation.DeadlineHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.messaging.annotation.MetaDataValue;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@NoArgsConstructor
@Getter
@Aggregate
public class AdvertisementAggregate {
    @AggregateIdentifier
    private String id;
    private String userId;
    private String categoryId;
    private String title;
    private String description;
    private String location;
    private BigDecimal price;
    private LocalDateTime timeOfAdd;
    private List<Photo> photoEntities;
    private AdvertisementStatus status;
    private String deadlineId;

    @CommandHandler
    public AdvertisementAggregate(CreateAdvertisementCommand c, DeadlineManager deadlineManager) {
        String deadlineId = deadlineManager.schedule(Duration.ofDays(c.daysOfActivity), "ad");
        AdvertisementCreatedEvent event =
                new AdvertisementCreatedEvent(
                        c.id, c.userId, c.categoryId, c.title, c.description, c.location,
                        c.daysOfActivity, c.price, c.timeOfAdd, c.status, c.photos);
        AggregateLifecycle.apply(event, MetaData.with("deadlineId", deadlineId));
    }

    @CommandHandler
    public void handle(ChangeAdvertisementCommand c) {
        if (this.status.equals(AdvertisementStatus.EXPIRED)) {
            throw new IllegalStateException("Advertisement expired");
        }
        AggregateLifecycle.apply(new AdvertisementChangedEvent(c.id, c.title, c.description, c.price));
    }

    @CommandHandler
    public void handle(DeleteAdvertisementPhotoCommand c) {
        if (this.status.equals(AdvertisementStatus.EXPIRED)) {
            throw new IllegalStateException("Advertisement expired");
        }
        AggregateLifecycle.apply(new AdvertisementPhotoDeletedEvent(c.id, c.photosIds));
    }

    @CommandHandler
    public void handle(AddAdvertisementPhotosCommand c) {
        if (this.status.equals(AdvertisementStatus.EXPIRED)) {
            throw new IllegalStateException("Advertisement expired");
        }
        AggregateLifecycle.apply(new AdvertisementPhotoAddedEvent(c.id, c.photos));
    }

    @EventSourcingHandler
    public void on(AdvertisementCreatedEvent event, @MetaDataValue("deadlineId") String deadlineId) {
        this.id = event.id;
        this.userId = event.userId;
        this.categoryId = event.categoryId;
        this.title = event.title;
        this.description = event.description;
        this.location = event.location;
        this.price = event.price;
        this.timeOfAdd = event.timeOfAdd;
        this.status = AdvertisementStatus.valueOf(event.status);
        this.photoEntities = new ArrayList<>(event.photos);
        this.deadlineId = deadlineId;
    }

    @EventSourcingHandler
    public void on(AdvertisementChangedEvent event) {
        this.title = event.title;
        this.description = event.description;
        this.price = event.price;
    }

    @EventSourcingHandler
    public void on(AdvertisementPhotoDeletedEvent event) {
        this.photoEntities = this.photoEntities.stream()
                .filter(p -> !event.photosIds.contains(p.getPublicId()))
                .collect(Collectors.toList());
    }

    @EventSourcingHandler
    public void on(AdvertisementPhotoAddedEvent event) {
        this.photoEntities.addAll(event.photos);
    }

    @DeadlineHandler(deadlineName = "ad")
    public void disableAdvertisement() {
        AggregateLifecycle.apply(new AdvertisementExpiredEvent(this.id));
    }

    @EventSourcingHandler
    public void on(AdvertisementExpiredEvent event) {
        this.status = AdvertisementStatus.EXPIRED;
    }
}
