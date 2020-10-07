package com.pik.controller;

import com.pik.advertisement.event.AdvertisementChangedEvent;
import com.pik.advertisement.event.AdvertisementCreatedEvent;
import com.pik.advertisement.event.AdvertisementPhotoAddedEvent;
import com.pik.advertisement.event.AdvertisementPhotoDeletedEvent;
import com.pik.aggregate.AdvertisementAggregate;
import com.pik.dto.AdvertisementDto;
import com.pik.dto.AdvertisementPhotosDto;
import com.pik.dto.ChangeAdvertisementDto;
import com.pik.dto.DeleteAdvertisementPhotosDto;
import com.pik.sender.AdvertisementCommandSender;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class AdvertisementController {
    private final AdvertisementCommandSender advertisementCommandSender;
    private final EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<Object> createAdvertisement(@RequestBody AdvertisementDto advertisementDto) {
        System.out.println(advertisementDto);
        return this.advertisementCommandSender.sendCreateAdvertisementCommand(advertisementDto);
    }

    @PutMapping("/change")
    public void changeAdvertisement(@RequestBody ChangeAdvertisementDto changeAdvertisementDto) {
        this.advertisementCommandSender.sendChangeAdvertisementCommand(changeAdvertisementDto);
    }

    @PutMapping("/add")
    public void addAdvertisementPhotos(@RequestBody AdvertisementPhotosDto advertisementPhotosDto) {
        this.advertisementCommandSender.sendAddPhotoCommand(advertisementPhotosDto);
    }

    @DeleteMapping("/delete")
    public void deletePhoto(@RequestBody DeleteAdvertisementPhotosDto advertisementPhotosDto) {
        this.advertisementCommandSender.sendDeletePhotoCommand(advertisementPhotosDto);
    }




    @GetMapping("ad/{id}")
    public void get(@PathVariable String id) {
        DomainEventStream domainEventStream = this.eventStore.readEvents(id);
        AdvertisementAggregate advertisementAggregate = checkEvent(domainEventStream);
        System.out.println(advertisementAggregate);
    }

    private AdvertisementAggregate checkEvent(DomainEventStream message) {
        AdvertisementAggregate advertisementAggregate = new AdvertisementAggregate();
        while (message.hasNext()) {
            DomainEventMessage<?> next = message.next();
            if (next.getPayloadType().equals(AdvertisementCreatedEvent.class)) {
                AdvertisementCreatedEvent createdEvent = (AdvertisementCreatedEvent) next.getPayload();
                advertisementAggregate.on(createdEvent, "not mapped");
            }
            if (next.getPayloadType().equals(AdvertisementPhotoDeletedEvent.class)) {
                advertisementAggregate.on((AdvertisementPhotoDeletedEvent) next.getPayload());
            }
            if (next.getPayloadType().equals(AdvertisementPhotoAddedEvent.class)) {
                advertisementAggregate.on((AdvertisementPhotoAddedEvent) next.getPayload());
            }
            if (next.getPayloadType().equals(AdvertisementChangedEvent.class)) {
                advertisementAggregate.on((AdvertisementChangedEvent) next.getPayload());
            }
        }
        return advertisementAggregate;
    }

}
