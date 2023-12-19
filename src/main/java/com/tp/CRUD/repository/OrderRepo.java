package com.tp.CRUD.repository;

import com.tp.CRUD.entity.Order;
import com.tp.CRUD.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

    Order findByCustomerIdAndOrderStatus(Long customer_id, OrderStatus orderStatus);


    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatuses);

    List<Order> findByCustomerIdAndOrderStatusIn(Long customer_id, List<OrderStatus> orderStatus);


    Optional<Order> findByTrackingId(UUID trackingId);

    List<Order> findByDateBetweenAndOrderStatus(Date startOfMonth, Date endOfMonth, OrderStatus status);

    Long countByOrderStatus(OrderStatus status);

}
