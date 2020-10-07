package com.pik.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Id
    @Column(name = "ID", nullable = false)
    private String id;
    @Column(name = "EMAIL", length = 50, nullable = false)
    private String email;
    @Column(name = "PASSWORD", length = 200, nullable = false)
    private String password;
    @Column(name = "STATUS", length = 20, nullable = false)
    private String status;
}
