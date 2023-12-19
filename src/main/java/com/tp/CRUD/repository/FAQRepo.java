package com.tp.CRUD.repository;

import com.tp.CRUD.entity.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FAQRepo extends JpaRepository<FAQ,Long> {

    List<FAQ> findAllByProductId(Long productId);
}
