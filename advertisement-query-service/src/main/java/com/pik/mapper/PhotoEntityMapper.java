package com.pik.mapper;

import com.pik.advertisement.Photo;
import com.pik.dto.PhotoDto;
import com.pik.entity.PhotoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhotoEntityMapper {
    public PhotoDto mapToPhotoDto(PhotoEntity photoEntity) {
        return new PhotoDto(photoEntity.getId(), photoEntity.getUrl());
    }

    public List<PhotoDto> mapToPhotosDtoList(List<PhotoEntity> photos) {
        return photos.stream()
                .map(this::mapToPhotoDto)
                .collect(Collectors.toList());
    }

    public List<PhotoEntity> mapToPhotosEntities(List<Photo> photos) {
        return photos.stream()
                .map(photo -> new PhotoEntity(photo.getPublicId(), photo.getUrl()))
                .collect(Collectors.toList());
    }
}
