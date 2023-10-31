package com.tp.CRUD.service;

import com.tp.CRUD.entity.Customer;
import com.tp.CRUD.entity.User_jwt;
import com.tp.CRUD.repository.CustomerRepo;
import com.tp.CRUD.repository.UserjwtRepo;
import com.tp.CRUD.request.JwtRequest;
import com.tp.CRUD.response.JwtResponse;
import com.tp.CRUD.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private UserjwtRepo userjwtRepo;

    @Autowired
    private CustomerRepo customerRepo;


    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Customer> customer = customerRepo.findByEmail(username);

        if (customer.isEmpty()) throw new UsernameNotFoundException("Username is not valid",null);

        return new User(customer.get().getEmail(),customer.get().getPassword(),new ArrayList<>());

    }


/*

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUsername();
        String userPassword = jwtRequest.getPassword();
        authenticate(userName,userPassword);

        final  UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken =  jwtUtil.generateToken(userDetails);

        User_jwt userJwt = userjwtRepo.findById(userName).get();

        return new JwtResponse(userJwt,newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User_jwt userJwt = userjwtRepo.findById(username).get();

        if (userJwt != null){

            return  new User(userJwt.getUsername(),userJwt.getPassword(),getAuthorities(userJwt));
        }else {
            throw new UsernameNotFoundException("Username is not valid");
        }

    }

    private Set getAuthorities(User_jwt userJwt){
        Set authorities = new HashSet<>();
        userJwt.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });

        return authorities;
    }

    private void authenticate(String userName,String userPassword )throws Exception{
        try {
            System.out.println("Authentication request - Username: " + userName);
            //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassword));
        } catch (DisabledException e){
            throw new Exception("User is disabled");
        }catch (BadCredentialsException e){
            throw new Exception("Bad credentials from user");
        }

    }

 */
}
