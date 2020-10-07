package com.pik.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdvertisementDto {
    private String userId;
    private String categoryId;
    private String title;
    private String description;
    private String location;
    private BigDecimal price;
    private int daysOfActivity;
    private List<PhotoDto> photos;
}
