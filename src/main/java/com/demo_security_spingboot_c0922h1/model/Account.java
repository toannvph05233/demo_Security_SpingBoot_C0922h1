package com.demo_security_spingboot_c0922h1.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String avatar;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}

