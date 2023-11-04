package com.tp.CRUD.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "category")
@Data

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Lob
    private String description;

    //@ToString.Exclude
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
