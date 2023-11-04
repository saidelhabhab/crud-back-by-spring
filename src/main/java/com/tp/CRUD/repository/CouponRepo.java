package com.tp.CRUD.repository;

import com.tp.CRUD.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepo extends JpaRepository<Coupon,Long> {

    boolean existsByCode(String code);

    Optional<Coupon> findByCode(String code);
}
