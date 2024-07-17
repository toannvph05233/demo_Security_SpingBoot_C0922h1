package com.giao_thong.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Violate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100000)
    private String behavior;
    private long price;
    private String status;
    @Column(length = 100000)
    private String address;
    @Column(length = 100000)
    private String image;
    private LocalDateTime time;

    @ManyToOne
    private Account account;
    @ManyToOne
    private Car car;
}
