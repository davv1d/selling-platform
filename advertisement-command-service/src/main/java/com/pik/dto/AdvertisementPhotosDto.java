package com.pik.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class AdvertisementPhotosDto {
    private String id;
    private List<PhotoDto> photos;
}
