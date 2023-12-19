package com.tp.CRUD.service.admin.adminOrder;

import com.tp.CRUD.entity.Order;
import com.tp.CRUD.enums.OrderStatus;
import com.tp.CRUD.repository.OrderRepo;
import com.tp.CRUD.request.OrderDto;
import com.tp.CRUD.response.AnalyticResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {

    @Autowired
    private OrderRepo orderRepo;


    @Override
    public List<OrderDto> getAllPlaceOrder() {
        List<Order> orderList = orderRepo.findAllByOrderStatusIn(List.of(OrderStatus.Placed, OrderStatus.shipped, OrderStatus.Delivered));

        return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
    }


    @Override
    public OrderDto changeOrderStatus(Long orderId, String status) {

        Optional<Order> optionalOrder = orderRepo.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();

            if (Objects.equals(status, "Shipped")) {
                order.setOrderStatus(OrderStatus.shipped);
            } else if (Objects.equals(status, "Delivered")) {
                order.setOrderStatus(OrderStatus.Delivered);
            }
            return orderRepo.save(order).getOrderDto();
        }
        return null;

    }


    @Override
    public AnalyticResponse calculateAnalytics() {
        LocalDate currentData = LocalDate.now();
        LocalDate previousMonthDate = currentData.minusMonths(1);

        Long currentMonthOrder = getTotalOrdersForMonth(currentData.getMonthValue(), currentData.getYear());
        Long previousMonthOrders = getTotalOrdersForMonth(previousMonthDate.getMonthValue(), previousMonthDate.getYear());

        Long currentMonthEarnings = getTotalEarningForMonth(currentData.getMonthValue(), currentData.getYear());
        Long previousMonthEarnings = getTotalEarningForMonth(previousMonthDate.getMonthValue(), previousMonthDate.getYear());

        Long placed = orderRepo.countByOrderStatus(OrderStatus.Placed);
        Long shipped = orderRepo.countByOrderStatus(OrderStatus.shipped);
        Long delivered = orderRepo.countByOrderStatus(OrderStatus.Delivered);

        return new AnalyticResponse(
                placed,
                shipped,
                delivered,
                currentMonthOrder,
                previousMonthOrders,
                currentMonthEarnings,
                previousMonthEarnings
                );
    }


    ////////////////////////////////////////////////////////////////

    public Long getTotalOrdersForMonth(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);


        Date startOfMonth = calendar.getTime();

        //move the calendar to the end of the specified month
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Date endOfMonth = calendar.getTime();

        List<Order> orders = orderRepo.findByDateBetweenAndOrderStatus(startOfMonth, endOfMonth, OrderStatus.Delivered);

        return (long) orders.size();

    }

    public Long getTotalEarningForMonth(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);


        Date startOfMonth = calendar.getTime();

        //move the calendar to the end of the specified month
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Date endOfMonth = calendar.getTime();

        List<Order> orders = orderRepo.findByDateBetweenAndOrderStatus(startOfMonth, endOfMonth, OrderStatus.Delivered);

       Long sum = 0L;

       for (Order order:orders){
           sum += order.getAmount();
       }
       return sum;
    }
}