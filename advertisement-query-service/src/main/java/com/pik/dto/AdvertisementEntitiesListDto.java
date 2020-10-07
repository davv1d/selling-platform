package com.pik.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdvertisementEntitiesListDto {
    private List<AdvertisementEntityDto> advertisementEntitiesDto;
}
