package com.tp.CRUD.service;

import com.tp.CRUD.entity.User_jwt;

public interface UserJwtService {

    User_jwt registerNewUserJwt(User_jwt userJwt);

    void initRolesAndRoles();
}
