package com.tp.CRUD.entity;

import com.tp.CRUD.request.WishlistDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY , optional = false)
    @JoinColumn( name = "product_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;


    @ManyToOne(fetch =  FetchType.LAZY , optional = false)
    @JoinColumn( name = "customer_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;


    public WishlistDto getWishlistDto(){
        WishlistDto wishlistDto = new WishlistDto();

        wishlistDto.setId(id);
        wishlistDto.setProductId(product.getId());
        wishlistDto.setCustomerId(customer.getId());
        wishlistDto.setPrice(product.getPrice());
        wishlistDto.setProductDescription(product.getDescription());
        wishlistDto.setProductName(product.getName());
        wishlistDto.setReturnedImg(product.getImg());

        return wishlistDto;

    }
}
