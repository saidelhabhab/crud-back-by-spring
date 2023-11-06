package com.tp.CRUD.controller.admin;

import com.tp.CRUD.request.OrderDto;
import com.tp.CRUD.service.admin.adminOrder.AdminOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
public class AdminOrderController {


    @Autowired
    private AdminOrderService adminOrderService;


    @GetMapping("placeOrders")
    public ResponseEntity<List<OrderDto>> getAllPlaceOrders(){
        return ResponseEntity.ok(adminOrderService.getAllPlaceOrder());
    }
}
