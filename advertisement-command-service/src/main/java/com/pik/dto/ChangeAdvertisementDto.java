package com.pik.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChangeAdvertisementDto {
    private String id;
    private String title;
    private String description;
    private BigDecimal price;
}
