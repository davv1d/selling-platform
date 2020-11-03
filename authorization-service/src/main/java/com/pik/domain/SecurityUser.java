package com.pik.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "SECURITY_USER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SecurityUser {
    @Id
    private String id;

    @NotNull
    @Column(name = "EMAIL", length = 50, unique = true)
    private String email;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @NotNull
    @Column(name = "ROLE")
    private String role;

    @NotNull
    @Column(name = "STATUS")
    private String userStatus;
}

