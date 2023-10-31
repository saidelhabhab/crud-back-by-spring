package com.tp.CRUD.controller;

import com.tp.CRUD.entity.Role;
import com.tp.CRUD.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;
    @PostMapping({"/createNewRole"})
    public ResponseEntity<Role> createNewRole(@RequestBody Role role){
        Role r =  roleService.createNewRole(role);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }

    @GetMapping("/all-role")
    public ResponseEntity<List<Role>> allRoles(){

        return ResponseEntity.ok().body(roleService.allRoles());
    }
}


