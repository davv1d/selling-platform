package com.pik.mapper;

import com.pik.dto.AdvertisementEntitiesListDto;
import com.pik.dto.AdvertisementEntityDto;
import com.pik.entity.AdvertisementEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AdvertisementEntityMapper {
    private final PhotoEntityMapper photoMapper;

    public AdvertisementEntityDto mapToAdvertisementEntityDto(AdvertisementEntity entity) {
        return new AdvertisementEntityDto(
                entity.getId(),
                entity.getUserId(),
                entity.getCategoryId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getTimeOfAdd(),
                entity.getStatus(),
                this.photoMapper.mapToPhotosDtoList(entity.getPhotos())
        );
    }

    public AdvertisementEntitiesListDto mapToAdvertisementsDtoList(List<AdvertisementEntity> advertisementEntities) {
        List<AdvertisementEntityDto> advertisementsEntitiesDto = advertisementEntities.stream()
                .map(this::mapToAdvertisementEntityDto)
                .collect(Collectors.toList());
        return new AdvertisementEntitiesListDto(advertisementsEntitiesDto);
    }
}
