package com.tp.CRUD.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tp.CRUD.request.ProductDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "product")
@Data

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long price;

    @Lob
    private String description;

    // @Basic(fetch = FetchType.EAGER)
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    //@ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "category_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    public ProductDto getDto(){

        ProductDto productDto = new ProductDto();

        productDto.setId(id);
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setPrice(price);
        productDto.setByteImg(img);
        productDto.setCategoryId(category.getId());
        productDto.setCategoryName(category.getName());
        return productDto;

    }
}
