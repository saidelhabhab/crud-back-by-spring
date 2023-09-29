package com.tp.CRUD.repository;

import com.tp.CRUD.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AvatarRepo extends JpaRepository<Avatar,Long> {
}
