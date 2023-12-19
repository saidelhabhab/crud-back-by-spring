package com.tp.CRUD.controller.custumer;

import com.tp.CRUD.request.WishlistDto;
import com.tp.CRUD.service.costumer.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer/")
public class WishlistController {

    private final WishlistService wishlistService;


    @PostMapping("wishlist")
    public ResponseEntity<?> addProductToWishlist(@RequestBody WishlistDto wishlistDto){
        WishlistDto postedWishlistDto = wishlistService.addProductToWishlist(wishlistDto);

        if (postedWishlistDto == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");

        return ResponseEntity.status(HttpStatus.CREATED).body(postedWishlistDto);
    }


    @GetMapping("wishlist/{customerId}")
    public ResponseEntity<List<WishlistDto>> getWishlistByCustomerId(@PathVariable Long customerId){
        return ResponseEntity.ok(wishlistService.getWishlistByCustomerId(customerId));
    }
}
