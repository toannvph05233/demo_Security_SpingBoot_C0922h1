package com.giao_thong.model;

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
    @Column(length = 100000)
    private String avatar;
    @Column(length = 100000)
    private String detection;
    @Column(length = 100000)
    private String addressHandle;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}

