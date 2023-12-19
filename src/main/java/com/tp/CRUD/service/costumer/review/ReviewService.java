package com.tp.CRUD.service.costumer.review;

import com.tp.CRUD.request.OrderProductResponseDto;
import com.tp.CRUD.request.ReviewDto;

import java.io.IOException;

public interface ReviewService {

    public OrderProductResponseDto getOrderProductsDetailsByOrderId(Long orderId);

    public ReviewDto giveReview(ReviewDto reviewDto) throws IOException;
}
