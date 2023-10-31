package com.tp.CRUD.service.authservice;


import com.tp.CRUD.request.CustomerDto;
import com.tp.CRUD.request.SignupRequest;

public interface AuthService {

    public CustomerDto createCustomer(SignupRequest signupRequest);
    public CustomerDto createAdminAccount();
    public Boolean hasUserWithEmail(String email);
}
