package com.pik.handler;

import com.pik.advertisement.AdvertisementStatus;
import com.pik.advertisement.event.*;
import com.pik.entity.AdvertisementEntity;
import com.pik.entity.PhotoEntity;
import com.pik.mapper.PhotoEntityMapper;
import com.pik.repository.AdvertisementRepository;
import com.pik.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AdvertisementEventHandler {
    private final AdvertisementRepository advertisementRepository;
    private final PhotoRepository photoRepository;
    private final PhotoEntityMapper photoEntityMapper;

    @EventHandler
    public void createAdvertisement(AdvertisementCreatedEvent event) {
        List<PhotoEntity> photoEntities = photoEntityMapper.mapToPhotosEntities(event.photos);
        AdvertisementEntity advertisementEntity = new AdvertisementEntity(
                event.id,
                event.userId,
                event.categoryId,
                event.title,
                event.description,
                event.price,
                event.timeOfAdd,
                event.status,
                photoEntities);
        advertisementRepository.save(advertisementEntity);
        System.out.println("save advertisement");
    }

    @EventHandler
    public void changeAdvertisement(AdvertisementChangedEvent event) {
        System.out.println("change advertisement");
        advertisementRepository.findById(event.id)
                .ifPresent(ad -> {
                    ad.setTitle(event.title);
                    ad.setDescription(event.description);
                    ad.setPrice(event.price);
                    advertisementRepository.save(ad);
                });
    }

    @EventHandler
    public void addPhotoToAdvertisement(AdvertisementPhotoAddedEvent event) {
        System.out.println("add photo");
        advertisementRepository.findById(event.id)
                .ifPresent(ad -> {
                    List<PhotoEntity> photoEntities = photoEntityMapper.mapToPhotosEntities(event.photos);
                    ad.getPhotos().addAll(photoEntities);
                    advertisementRepository.save(ad);
                });
    }

    @EventHandler
    public void deletePhotoFromAdvertisement(AdvertisementPhotoDeletedEvent event) {
        System.out.println("delete photo");
        advertisementRepository.findById(event.id)
                .ifPresent(ad -> {
                    List<PhotoEntity> collect = ad.getPhotos().stream()
                            .filter(photoEntity -> !event.photosIds.contains(photoEntity.getId()))
                            .collect(Collectors.toList());
                    ad.setPhotos(collect);
                    advertisementRepository.save(ad);
                });

        photoRepository.findAllById(event.photosIds)
                .forEach(photoEntity -> photoRepository.deleteById(photoEntity.getId()));
    }

    @EventHandler
    public void changeAdvertisementStatus(AdvertisementExpiredEvent event) {
        System.out.println("advertisement expired");
        advertisementRepository.findById(event.id)
                .ifPresent(ad -> {
                    ad.setStatus(AdvertisementStatus.EXPIRED.toString());
                    advertisementRepository.save(ad);
                });
    }

}
