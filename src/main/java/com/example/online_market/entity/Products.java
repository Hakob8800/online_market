package com.example.online_market.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String title;
    private String imgPath;
    private Double price;
    private String description;
    @ManyToOne
    private Category category;
}
