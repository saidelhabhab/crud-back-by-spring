package com.tp.CRUD.repository;

import com.tp.CRUD.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ProductRepo extends JpaRepository<Product ,Long> {
}
