package com.tp.CRUD.service.costumer.review;

import com.tp.CRUD.entity.*;
import com.tp.CRUD.repository.CustomerRepo;
import com.tp.CRUD.repository.OrderRepo;
import com.tp.CRUD.repository.ProductRepo;
import com.tp.CRUD.repository.ReviewRepo;
import com.tp.CRUD.request.OrderProductResponseDto;
import com.tp.CRUD.request.ProductDto;
import com.tp.CRUD.request.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements  ReviewService{

    private final OrderRepo orderRepo;

    private final ProductRepo productRepo;

    private final CustomerRepo customerRepo;

    private final ReviewRepo reviewRepo;


    @Override
    public OrderProductResponseDto getOrderProductsDetailsByOrderId(Long orderId){
        Optional<Order> optionalOrder = orderRepo.findById(orderId);

        OrderProductResponseDto orderProductResponseDto = new OrderProductResponseDto();
        if (optionalOrder.isPresent()){
            orderProductResponseDto.setOrderAmount(optionalOrder.get().getAmount());

            List<ProductDto> productDtoList = new ArrayList<>();

            for(CartItems cartItems: optionalOrder.get().getCartItems()){

                ProductDto productDto = new ProductDto();
                productDto.setId(cartItems.getProduct().getId());
                productDto.setName(cartItems.getProduct().getName());
                productDto.setPrice(cartItems.getPrice());
                productDto.setQuantity(cartItems.getQuantity());

                productDto.setByteImg(cartItems.getProduct().getImg());

                productDtoList.add(productDto);
            }

            orderProductResponseDto.setProductDtoList(productDtoList);
        }
        return orderProductResponseDto;
    }

    @Override
    public ReviewDto giveReview(ReviewDto reviewDto) throws IOException {
        Optional<Product> optionalProduct = productRepo.findById(reviewDto.getProductId());
        Optional<Customer> optionalCustomer = customerRepo.findById(reviewDto.getCustomerId());

        if (optionalCustomer.isPresent() && optionalProduct.isPresent()){
            Review review = new Review();

            review.setRating(reviewDto.getRating());
            review.setDescription(reviewDto.getDescription());
            review.setCustomer(optionalCustomer.get());
            review.setProduct(optionalProduct.get());
            review.setImg(reviewDto.getImg().getBytes());

            return reviewRepo.save(review).getDto();
        }
        else {
            return  null;
        }
    }
}
