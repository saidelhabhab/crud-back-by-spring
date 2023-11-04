package com.tp.CRUD.controller.admin;

import com.tp.CRUD.entity.Coupon;
import com.tp.CRUD.exception.ValidationException;
import com.tp.CRUD.service.admin.coupon.AdminCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/coupons")
public class AdminCouponController {

    @Autowired
    private AdminCouponService couponService;


    @PostMapping
    public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon) {

        try {
            Coupon createCoupon = couponService.createCoupon(coupon);
            return ResponseEntity.ok(createCoupon);
        }catch (ValidationException ex){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<List<Coupon>> getAllCoupons(){
        return  ResponseEntity.ok(couponService.getAllCoupons());
    }
}
