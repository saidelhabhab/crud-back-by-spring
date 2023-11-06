package com.tp.CRUD.repository;

import com.tp.CRUD.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product ,Long> {


    @Query("SELECT p FROM Product p WHERE p.name LIKE %:productName%")
    List<Product> findAllNameContaining(@Param("productName") String title);
}
