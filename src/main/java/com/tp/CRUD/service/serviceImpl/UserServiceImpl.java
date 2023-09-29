package com.tp.CRUD.service.serviceImpl;

import com.tp.CRUD.entity.User;
import com.tp.CRUD.exception.ResourceNotFoundException;
import com.tp.CRUD.repository.UserRepo;
import com.tp.CRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepo userRepo;
    @Override
    public User save(User newUser) throws IOException {

        User user = userRepo.save(newUser);
        Date date = new Date();
        user.setDate(date);
        return userRepo.save(newUser);
    }

    @Override
    public List<User> getAll() {
        List<User> users =userRepo.findAll();
        return users;
    }

    @Override
    public User update(User user, Long id) {
        User user1 = userRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("user not found"));

        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());
        user1.setPhone(user.getPhone());
        Date date = new Date();
        user1.setDate(date);
        return userRepo.save(user1);
    }

    @Override
    public User getIdImage(Long id) {

        User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));


        return user;
    }

    @Override
    public User getVideoById(Long id) {
        User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        return user;
    }

    @Override
    public void delete(Long id) {

        userRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("user not found"));

        userRepo.deleteById(id);
    }


}
