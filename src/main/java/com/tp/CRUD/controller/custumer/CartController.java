package com.tp.CRUD.controller.custumer;

import com.tp.CRUD.exception.ValidationException;
import com.tp.CRUD.request.AddProductInCartDto;
import com.tp.CRUD.request.OrderDto;
import com.tp.CRUD.request.PlaceOrder;
import com.tp.CRUD.service.costumer.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer/")
@CrossOrigin
@RequiredArgsConstructor
public class CartController {


    private final CartService cartService;



    @PostMapping("cart")
    public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCartDto){
        return  cartService.addProductToCart(addProductInCartDto);
    }

    @GetMapping("hello")
    public String getHello(){
        return " im here";
    }


    @GetMapping("cart/{customerId}")
    public ResponseEntity<?> addProductToCart(@PathVariable Long customerId ){
        OrderDto orderDto = cartService.getCartByCustomerId(customerId);
        return  ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @GetMapping("coupon/{customerId}/{code}")
    public ResponseEntity<?> applyCoupon( @PathVariable Long customerId, @PathVariable String code){
        try {
            OrderDto orderDto = cartService.ApplyCoupon(customerId,code);
            return  ResponseEntity.ok(orderDto);
        }catch (ValidationException ex){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

    }


    @PostMapping("addition")
    public ResponseEntity<OrderDto>  increaseProductQuantity(@RequestBody  AddProductInCartDto addProductInCartDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increaseProductQuantity(addProductInCartDto));

    }

    @PostMapping("deduction")
    public ResponseEntity<OrderDto>  decreaseProductQuantity(@RequestBody  AddProductInCartDto addProductInCartDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.decreaseProductQuantity(addProductInCartDto));

    }

    @PostMapping("placeOrder")
    public ResponseEntity<OrderDto>  decreaseProductQuantity(@RequestBody PlaceOrder placeOrder){

        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeOrder(placeOrder));
    }


    @GetMapping("MyOrders/{customerId}")
    public ResponseEntity<List<OrderDto>> getMyPlaceOrders(@PathVariable Long customerId){

        return ResponseEntity.ok(cartService.getMyPlaceOrders(customerId));
    }




}
