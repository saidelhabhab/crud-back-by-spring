package com.tp.CRUD.repository;

import com.tp.CRUD.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist,Long> {


    List<Wishlist> findAllByCustomerId(Long customerId);
}
