package com.pik.handler;

import com.pik.advertisement.event.AdvertisementPhotoDeletedEvent;
import com.pik.service.PhotoUploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PhotoUploadEventHandler {
    private final PhotoUploader photoUploader;

    @EventHandler
    public void deletePhoto(AdvertisementPhotoDeletedEvent event) {
        event.photosIds.stream()
                .map(photoUploader::deletePhoto)
                .forEach(map -> {
                    if (map.get("result").equals("ok")) {
                        log.info("DELETE PHOTO");
                    } else {
                        log.info("NOT FOUND PHOTO");
                    }
                });
    }
}
