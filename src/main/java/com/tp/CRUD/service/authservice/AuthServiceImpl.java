package com.tp.CRUD.service.authservice;


import com.tp.CRUD.entity.Customer;
import com.tp.CRUD.entity.Order;
import com.tp.CRUD.enums.OrderStatus;
import com.tp.CRUD.enums.UserRole;
import com.tp.CRUD.repository.CustomerRepo;
import com.tp.CRUD.repository.OrderRepo;
import com.tp.CRUD.request.CustomerDto;
import com.tp.CRUD.request.SignupRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public CustomerDto createCustomer(SignupRequest signupRequest){
        Customer customer = new Customer();

        customer.setEmail(signupRequest.getEmail());
        customer.setName(signupRequest.getName());
        customer.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        customer.setRole(UserRole.CUSTOMER);

        Customer createUser=customerRepo.save(customer);

        ///////////////Order ////////////////////
        Order order = new Order();
        order.setAmount(0L);
        order.setTotalAmount(0L);
        order.setDiscount(0L);
        order.setCustomer(createUser);
        order.setOrderStatus(OrderStatus.Pending);
        orderRepo.save(order);

        CustomerDto customerDto= new CustomerDto();
        customerDto.setId(createUser.getId());

        return  customerDto;

    }

    @Override
    @PostConstruct
    public CustomerDto createAdminAccount() {
        Customer admin = customerRepo.findByRole(UserRole.ADMIN);
        if (admin== null){
            Customer customer= new Customer();
            customer.setName("admin");
            customer.setEmail("admin@admin.ma");
            customer.setPassword(passwordEncoder.encode("12345"));
            customer.setRole(UserRole.ADMIN);

            customerRepo.save(customer);
        }

        return null;
    }

    @Override
    public Boolean hasUserWithEmail(String email) {
        return customerRepo.findByEmail(email).isPresent();
    }
}
