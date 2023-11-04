package com.tp.CRUD.service.admin.coupon;

import com.tp.CRUD.entity.Coupon;
import com.tp.CRUD.exception.ValidationException;
import com.tp.CRUD.repository.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCouponServiceImpl implements AdminCouponService{

    @Autowired
    private CouponRepo couponRepo;


    @Override
    public Coupon createCoupon(Coupon coupon){

        if (couponRepo.existsByCode(coupon.getCode())){
            throw  new ValidationException("Coupon code already exists ");
        }
        return couponRepo.save(coupon);
    }

    @Override
    public List<Coupon> getAllCoupons(){
        return couponRepo.findAll();
    }
}
