package com.pik.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdvertisementEntityDto {
    private String id;
    private String userId;
    private String categoryId;
    private String title;
    private String description;
    private BigDecimal price;
    private LocalDateTime timeOfAdd;
    private String status;
    private List<PhotoDto> photos = new ArrayList<>();
}
