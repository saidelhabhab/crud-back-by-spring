package com.tp.CRUD.service.admin.coupon;

import com.tp.CRUD.entity.Coupon;

import java.util.List;

public interface AdminCouponService {

    public Coupon createCoupon(Coupon coupon);

    public List<Coupon> getAllCoupons();
}
