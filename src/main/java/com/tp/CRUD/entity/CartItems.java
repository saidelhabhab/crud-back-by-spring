package com.tp.CRUD.entity;

import com.tp.CRUD.request.CartItemsDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "cartItem")
@Data
public class CartItems {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long price;
    private Long quantity;

    //@ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name ="product_id",nullable = false )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    //@ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name ="customer_id",nullable = false )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    //@ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;



    public CartItemsDto getCartDto(){
        CartItemsDto cartItemsDto = new CartItemsDto();

        cartItemsDto.setId(id);
        cartItemsDto.setPrice(price);
        cartItemsDto.setProductId(product.getId());
        cartItemsDto.setQuantity(quantity);
        cartItemsDto.setCustomerId(customer.getId());
        cartItemsDto.setProductName(product.getName());
        cartItemsDto.setReturnedImg(product.getImg());

        return cartItemsDto;
    }

}
