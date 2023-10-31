package com.tp.CRUD.service;

import com.tp.CRUD.entity.Role;

import java.util.List;

public interface RoleService {

    Role createNewRole(Role role);
    List<Role> allRoles();
}
