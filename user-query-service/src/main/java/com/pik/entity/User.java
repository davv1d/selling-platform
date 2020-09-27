package com.pik.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
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

    public User() {
    }

    public User(String id, String email, String password, String status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }
}
