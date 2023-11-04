package com.tp.CRUD.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Table(name = "coupons")
@Data
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private Long discount;
    private Date expirationDate;
}
