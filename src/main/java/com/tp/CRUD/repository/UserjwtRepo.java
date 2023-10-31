package com.tp.CRUD.repository;

import com.tp.CRUD.entity.User_jwt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserjwtRepo extends JpaRepository<User_jwt,String> {
}
