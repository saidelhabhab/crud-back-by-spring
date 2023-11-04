package com.tp.CRUD.repository;

import com.tp.CRUD.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemsRepo extends JpaRepository<CartItems,Long> {

    Optional<CartItems>  findByProductIdAndOrderIdAndCustomerId(Long productId, Long orderId, Long customer_id);
}
