package com.pik.mapper;

import com.pik.advertisement.Photo;
import com.pik.dto.PhotoDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhotoMapper {

    public List<Photo> mapToPhotosList(List<PhotoDto> photosDto) {
        return photosDto.stream()
                .map(this::mapToPhoto)
                .collect(Collectors.toList());
    }

    private Photo mapToPhoto(PhotoDto photoDto) {
        return new Photo(photoDto.getPublicId(), photoDto.getUrl());
    }
}
