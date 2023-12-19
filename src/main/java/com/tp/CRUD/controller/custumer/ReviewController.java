package com.tp.CRUD.controller.custumer;

import com.tp.CRUD.request.OrderProductResponseDto;
import com.tp.CRUD.request.ReviewDto;
import com.tp.CRUD.service.costumer.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer/")
public class ReviewController {

    private final ReviewService reviewService;


    @GetMapping("order-products/{orderId}")
    public ResponseEntity<OrderProductResponseDto> getOrderProductsDetailsByOrderId(@PathVariable Long orderId){
        return ResponseEntity.ok(reviewService.getOrderProductsDetailsByOrderId(orderId));
    }

    @PostMapping("review")
    public ResponseEntity<?> giveReview(ReviewDto reviewDto) throws IOException{
        ReviewDto reviewDto1 = reviewService.giveReview(reviewDto);
        if (reviewDto1== null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto1);
    }
}
