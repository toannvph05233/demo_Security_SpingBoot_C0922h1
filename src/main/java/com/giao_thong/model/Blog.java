package com.giao_thong.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column(length = 100000)
    private String content;
    @Column(length = 100000)
    private String img;

    @ManyToOne
    private Category category;
}
