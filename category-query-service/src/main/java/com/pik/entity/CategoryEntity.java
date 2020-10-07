package com.pik.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "CATEGORIES")
public class CategoryEntity {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "NAME")
    private String name;
}
