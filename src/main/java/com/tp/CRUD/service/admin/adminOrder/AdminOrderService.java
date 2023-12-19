package com.tp.CRUD.service.admin.adminOrder;

import com.tp.CRUD.request.OrderDto;
import com.tp.CRUD.response.AnalyticResponse;

import java.util.List;

public interface AdminOrderService {

    public List<OrderDto> getAllPlaceOrder();

    public OrderDto changeOrderStatus(Long orderId , String status);

    public AnalyticResponse calculateAnalytics();
}
