package com.giao_thong.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime time;
    private long total;
    @ManyToOne
    private Car car;
    @ManyToOne
    private Violate violate;
}
