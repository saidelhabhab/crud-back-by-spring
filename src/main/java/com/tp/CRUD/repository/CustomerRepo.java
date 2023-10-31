package com.tp.CRUD.repository;

import com.tp.CRUD.entity.Customer;
import com.tp.CRUD.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {


    Optional<Customer> findByEmail(String email);

    Customer findByRole(UserRole userRole);


}
