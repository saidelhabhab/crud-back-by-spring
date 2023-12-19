package com.tp.CRUD.repository;


import com.tp.CRUD.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Long> {

    List<Review> findAllByProductId(Long productId);
}
