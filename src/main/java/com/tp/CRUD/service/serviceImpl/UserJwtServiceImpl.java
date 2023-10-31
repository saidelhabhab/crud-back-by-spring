package com.tp.CRUD.service.serviceImpl;

import com.tp.CRUD.entity.Role;
import com.tp.CRUD.entity.User_jwt;
import com.tp.CRUD.repository.RoleRepo;
import com.tp.CRUD.repository.UserjwtRepo;
import com.tp.CRUD.service.UserJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserJwtServiceImpl implements UserJwtService {

    @Autowired
    private UserjwtRepo userjwtRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User_jwt registerNewUserJwt(User_jwt userJwt) {
        return userjwtRepo.save(userJwt);
    }
    @Override
    public void initRolesAndRoles(){

        Role amdinRole = new Role("Admin","Admin role from Init");
        roleRepo.save(amdinRole);

        Role userRole = new Role("User","Default role for newly created from Init");
        roleRepo.save(userRole);

        User_jwt adminUser = new User_jwt();
        adminUser.setUsername("admin123");
        adminUser.setFullName("administrator");
        adminUser.setPassword(getEncodedPassword("12345"));
        adminUser.setEmail("admin@admin.ma");
        Set<Role> admin_Roles= new HashSet<>();
        admin_Roles.add(amdinRole);
        adminUser.setRole(admin_Roles);
        userjwtRepo.save(adminUser);

        User_jwt user = new User_jwt();
        user.setUsername("user123");
        user.setFullName("user");
        user.setPassword(getEncodedPassword("12345"));
        user.setEmail("user@user.ma");
        Set<Role> user_Roles= new HashSet<>();
        user_Roles.add(userRole);
        user.setRole(user_Roles);
        userjwtRepo.save(user);


    }

    public  String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }
}
