package com.pik.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "PHOTOS")
public class PhotoEntity {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "URL")
    private String url;
}
