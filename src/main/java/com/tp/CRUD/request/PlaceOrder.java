package com.tp.CRUD.request;

import lombok.Data;

@Data
public class PlaceOrder {

    private Long customerId;

    private String address;

    private String orderDescription;
}
