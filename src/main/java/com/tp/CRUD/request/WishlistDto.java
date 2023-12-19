package com.tp.CRUD.request;

import lombok.Data;

@Data
public class WishlistDto {

    private Long id;

    private Long productId;

    private Long customerId;

    private String productName;

    private String productDescription;

    private byte[] returnedImg;

    private Long price;
}
