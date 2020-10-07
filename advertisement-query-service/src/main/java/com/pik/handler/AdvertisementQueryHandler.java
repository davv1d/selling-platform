package com.pik.handler;

import com.pik.advertisement.query.FindAdvertisementById;
import com.pik.advertisement.query.FindAdvertisementByUser;
import com.pik.advertisement.query.FindAdvertisements;
import com.pik.advertisement.query.FindAdvertisementsByCategory;
import com.pik.dto.AdvertisementEntitiesListDto;
import com.pik.dto.AdvertisementEntityDto;
import com.pik.entity.AdvertisementEntity;
import com.pik.mapper.AdvertisementEntityMapper;
import com.pik.repository.AdvertisementRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdvertisementQueryHandler {
    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementEntityMapper advertisementEntityMapper;

    @QueryHandler
    public AdvertisementEntitiesListDto handle(FindAdvertisements findAdvertisements) {
        List<AdvertisementEntity> advertisements = this.advertisementRepository.findAll();
        return this.advertisementEntityMapper.mapToAdvertisementsDtoList(advertisements);
    }

    @QueryHandler
    public AdvertisementEntityDto handle(FindAdvertisementById findAdvertisementById) {
        AdvertisementEntity advertisement = this.advertisementRepository.findById(findAdvertisementById.id).orElse(new AdvertisementEntity());
        return this.advertisementEntityMapper.mapToAdvertisementEntityDto(advertisement);
    }

    @QueryHandler
    public AdvertisementEntitiesListDto handle(FindAdvertisementsByCategory findAdvertisements) {
        List<AdvertisementEntity> advertisements = this.advertisementRepository.findByCategoryId(findAdvertisements.categoryId);
        return this.advertisementEntityMapper.mapToAdvertisementsDtoList(advertisements);
    }

    @QueryHandler
    public AdvertisementEntitiesListDto handle(FindAdvertisementByUser findAdvertisements) {
        List<AdvertisementEntity> advertisements = this.advertisementRepository.findByUserId(findAdvertisements.userId);
        return this.advertisementEntityMapper.mapToAdvertisementsDtoList(advertisements);
    }
}
