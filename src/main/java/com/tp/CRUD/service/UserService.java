package com.tp.CRUD.service;

import com.tp.CRUD.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {

    User save(User newUser) throws IOException;

    List<User> getAll();

    User update(User user, Long id);

    User getIdImage(Long id);

    User getVideoById(Long id);

    void delete(Long id);
}
