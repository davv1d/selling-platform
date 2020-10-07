package com.pik.mapper;

import com.pik.advertisement.AdvertisementStatus;
import com.pik.advertisement.Photo;
import com.pik.advertisement.command.AddAdvertisementPhotosCommand;
import com.pik.advertisement.command.ChangeAdvertisementCommand;
import com.pik.advertisement.command.CreateAdvertisementCommand;
import com.pik.advertisement.command.DeleteAdvertisementPhotoCommand;
import com.pik.dto.AdvertisementDto;
import com.pik.dto.AdvertisementPhotosDto;
import com.pik.dto.ChangeAdvertisementDto;
import com.pik.dto.DeleteAdvertisementPhotosDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AdvertisementCommandBuilder {
    private final PhotoMapper photoMapper;

    public CreateAdvertisementCommand buildCreateAdvertisementCommand(AdvertisementDto advertisementDto) {
        String id = UUID.randomUUID().toString();
        List<Photo> photos = this.photoMapper.mapToPhotosList(advertisementDto.getPhotos());
        return new CreateAdvertisementCommand(
                id,
                advertisementDto.getUserId(),
                advertisementDto.getCategoryId(),
                advertisementDto.getTitle(),
                advertisementDto.getDescription(),
                advertisementDto.getLocation(),
                advertisementDto.getDaysOfActivity(),
                advertisementDto.getPrice(),
                LocalDateTime.now(),
                AdvertisementStatus.ACTIVE.toString(),
                photos);
    }

    public ChangeAdvertisementCommand buildChangeAdvertisementCommand(ChangeAdvertisementDto changeAdvertisementDto) {
        return new ChangeAdvertisementCommand(
                changeAdvertisementDto.getId(),
                changeAdvertisementDto.getTitle(),
                changeAdvertisementDto.getDescription(),
                changeAdvertisementDto.getPrice()
        );
    }

    public AddAdvertisementPhotosCommand buildAddAdvertisementPhotosCommand(AdvertisementPhotosDto advertisementPhotosDto) {
        return new AddAdvertisementPhotosCommand(
                advertisementPhotosDto.getId(),
                this.photoMapper.mapToPhotosList(advertisementPhotosDto.getPhotos())
        );
    }

    public DeleteAdvertisementPhotoCommand buildDeleteAdvertisementPhotoCommand(DeleteAdvertisementPhotosDto deleteAdvertisementPhotosDto) {
        return new DeleteAdvertisementPhotoCommand(
                deleteAdvertisementPhotosDto.getId(),
                deleteAdvertisementPhotosDto.getPhotoIds());
    }
}
