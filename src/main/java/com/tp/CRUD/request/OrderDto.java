package com.tp.CRUD.request;

import com.tp.CRUD.entity.CartItems;
import com.tp.CRUD.entity.Customer;
import com.tp.CRUD.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {
    private Long id;

    private String orderDescription;
    private Date date;
    private Long amount;
    private String address;
    private OrderStatus orderStatus;
    private Long totalAmount;
    private Long discount;
    private UUID trackingId;


    private String customerName;


    private List<CartItemsDto> cartItems;

    private String couponName;

}
