package com.tp.CRUD.service.costumer.cart;

import com.tp.CRUD.request.AddProductInCartDto;
import com.tp.CRUD.request.OrderDto;
import com.tp.CRUD.request.PlaceOrder;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CartService {

    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);

    public OrderDto getCartByCustomerId(Long customerId);


    public OrderDto ApplyCoupon(Long customerId,String code);

    public OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto);

    public OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);

    public OrderDto placeOrder(PlaceOrder placeOrder);

    public List<OrderDto> getMyPlaceOrders(Long customerId);

    public OrderDto searchOrderByTrackingId(UUID trackingId);
}
