package com.pik.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "ADVERTISEMENTS")
public class AdvertisementEntity {
    @Id
    @Column(name = "ID", nullable = false)
    private String id;
    @Column(name = "USER_ID", nullable = false)
    private String userId;
    @Column(name = "CATEGORY_ID", nullable = false)
    private String categoryId;
    @Column(name = "TITLE", nullable = false)
    private String title;
    @Column(name = "DESCRIPTION", length = 400, nullable = false)
    private String description;
    @Column(name = "PRICE", scale = 2)
    private BigDecimal price;
    @Column(name = "TIME_OF_ADD")
    private LocalDateTime timeOfAdd;
    @Column(name = "STATUS")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ADVERTISEMENT_ID")
    private List<PhotoEntity> photos = new ArrayList<>();
}
