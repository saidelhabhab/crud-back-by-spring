package com.tp.CRUD.service.admin.adminOrder;

import com.tp.CRUD.entity.Order;
import com.tp.CRUD.enums.OrderStatus;
import com.tp.CRUD.repository.OrderRepo;
import com.tp.CRUD.request.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminOrderServiceImpl implements AdminOrderService{

    @Autowired
    private OrderRepo orderRepo;


    @Override
    public List<OrderDto> getAllPlaceOrder(){
        List<Order> orderList = orderRepo.findAllByOrderStatusIn(List.of(OrderStatus.Placed,OrderStatus.shipped,OrderStatus.Delivered));

        return  orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
    }
}
