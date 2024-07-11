package com.giao_thong.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String licensePlate;
    private String category;
    @Column(length = 100000)
    private String address;
    @Column(length = 100000)
    private String image;
    private String status;
    private Date date;
}
