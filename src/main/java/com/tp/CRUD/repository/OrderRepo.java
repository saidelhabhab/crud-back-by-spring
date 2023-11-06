package com.tp.CRUD.repository;

import com.tp.CRUD.entity.Order;
import com.tp.CRUD.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

    Order findByCustomerIdAndOrderStatus(Long customer_id, OrderStatus orderStatus);


    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatuses);
}
