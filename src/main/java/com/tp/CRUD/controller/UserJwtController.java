package com.tp.CRUD.controller;


import com.tp.CRUD.entity.Customer;
import com.tp.CRUD.entity.User_jwt;
import com.tp.CRUD.repository.CustomerRepo;
import com.tp.CRUD.request.CustomerDto;
import com.tp.CRUD.request.JwtRequest;
import com.tp.CRUD.request.SignupRequest;
import com.tp.CRUD.service.JwtService;
import com.tp.CRUD.service.UserJwtService;
import com.tp.CRUD.service.authservice.AuthService;
import com.tp.CRUD.util.JwtUtil;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class UserJwtController {


    private final AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;
    @Autowired
    private UserJwtService userJwtService ;

    public static final String TOKEN_PREFIX ="Bearer ";
    public static final String WEADER_STRING ="Authorization";

    /////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody JwtRequest jwtRequest, HttpServletResponse response) throws IOException, JSONException {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    jwtRequest.getUsername(),jwtRequest.getPassword()));

        }catch (BadCredentialsException e){
            throw new BadCredentialsException("incorrect username or PASS");
        }

        final UserDetails userDetails =  jwtService.loadUserByUsername(jwtRequest.getUsername());
        System.out.println("userDetails is show for me : "+userDetails);
        Optional<Customer> optionalCustomer = customerRepo.findByEmail(userDetails.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        if (optionalCustomer.isPresent()){
            response.getWriter().write(new JSONObject()
                    .put("customerId",optionalCustomer.get().getId())
                    .put("role",optionalCustomer.get().getRole())
                    .toString());
        }
        // add in final
        response.addHeader("Access-Control-Expose-Headers","Authorization");
        response.addHeader("Access-Control-Allow-Headers","Authorization, X-PINGOTHER, Origin," +
                "X-Requested-With, Content-Type, Accept, X-Custom-header");
        response.addHeader(WEADER_STRING,TOKEN_PREFIX + jwt);
        //System.out.println("response is "+ response);
    }


    @PostMapping("/sign-up")
    public  ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
        if (authService.hasUserWithEmail(signupRequest.getEmail())){
            return new ResponseEntity<>("User already exists ",HttpStatus.NOT_ACCEPTABLE);
        }

        CustomerDto customerDto = authService.createCustomer(signupRequest);
        return  new ResponseEntity<>(customerDto,HttpStatus.OK);
    }




    ///////////////////////////////////////////////////////////////////////////////////////////////

    @PostConstruct
    private void initRolesAndRoles(){
        userJwtService.initRolesAndRoles();
    }


    @PostMapping("/registerNewUser")
    public ResponseEntity<User_jwt> createUserJwt(@RequestBody User_jwt userJwt){

        User_jwt user = userJwtService.registerNewUserJwt(userJwt);

        return  new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/forAdmin")
    public String forAdmin(){
        return "tis URL only accessible to admin";
    }


    @GetMapping("/forUser")
    public String forUser(){
        return "tis URL only accessible to User";
    }
}
