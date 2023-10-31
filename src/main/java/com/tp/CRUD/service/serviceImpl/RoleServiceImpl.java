package com.tp.CRUD.service.serviceImpl;

import com.tp.CRUD.entity.Role;
import com.tp.CRUD.repository.RoleRepo;
import com.tp.CRUD.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;
    @Override
    public Role createNewRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public List<Role> allRoles() {
        return roleRepo.findAll();
    }
}
