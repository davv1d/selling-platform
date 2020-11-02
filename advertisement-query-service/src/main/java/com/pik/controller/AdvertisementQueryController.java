package com.pik.controller;

import com.pik.advertisement.query.FindAdvertisementById;
import com.pik.advertisement.query.FindAdvertisementByUser;
import com.pik.advertisement.query.FindAdvertisements;
import com.pik.advertisement.query.FindAdvertisementsByCategory;
import com.pik.dto.AdvertisementEntitiesListDto;
import com.pik.dto.AdvertisementEntityDto;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AdvertisementQueryController {
    private final QueryGateway queryGateway;

    @GetMapping("/")
    public CompletableFuture<AdvertisementEntitiesListDto> getAdvertisements() {
        return this.queryGateway.query(new FindAdvertisements(), ResponseTypes.instanceOf(AdvertisementEntitiesListDto.class));
    }

    @GetMapping("/{advertisementId}")
    public CompletableFuture<AdvertisementEntityDto> getAdvertisementById(@PathVariable String advertisementId) {
        return this.queryGateway.query(new FindAdvertisementById(advertisementId), ResponseTypes.instanceOf(AdvertisementEntityDto.class));
    }

    @GetMapping("/category/{categoryId}")
    public CompletableFuture<AdvertisementEntitiesListDto> getAdvertisementsByCategoryId(@PathVariable String categoryId) {
        return this.queryGateway.query(new FindAdvertisementsByCategory(categoryId), ResponseTypes.instanceOf(AdvertisementEntitiesListDto.class));
    }

    @GetMapping("/user/{userId}")
    public CompletableFuture<AdvertisementEntitiesListDto> getAdvertisementsByUserId(@PathVariable String userId) {
        return this.queryGateway.query(new FindAdvertisementByUser(userId), ResponseTypes.instanceOf(AdvertisementEntitiesListDto.class));
    }
}
