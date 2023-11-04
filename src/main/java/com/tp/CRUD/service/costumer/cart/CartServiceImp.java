package com.tp.CRUD.service.costumer.cart;

import com.tp.CRUD.entity.*;
import com.tp.CRUD.enums.OrderStatus;
import com.tp.CRUD.exception.ValidationException;
import com.tp.CRUD.repository.*;
import com.tp.CRUD.request.AddProductInCartDto;
import com.tp.CRUD.request.CartItemsDto;
import com.tp.CRUD.request.OrderDto;
import com.tp.CRUD.request.PlaceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartServiceImp implements CartService{

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CartItemsRepo cartItemsRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CouponRepo couponRepo;



    @Override
    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto){

        Order activeOrder = orderRepo.findByCustomerIdAndOrderStatus(addProductInCartDto.getCustomerId(), OrderStatus.Pending);
        System.out.println("activeOrder "+ activeOrder);

        Optional<CartItems> optionalCartItems = cartItemsRepo.findByProductIdAndOrderIdAndCustomerId(
                addProductInCartDto.getProductId(),activeOrder.getId(),addProductInCartDto.getCustomerId());
        System.out.println("optionalCartItems "+optionalCartItems);

        if (optionalCartItems.isPresent()){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }else {
            Optional<Product> optionalProduct = productRepo.findById(addProductInCartDto.getProductId());
            Optional<Customer> customerOptional = customerRepo.findById(addProductInCartDto.getCustomerId());

            if (optionalProduct.isPresent() && customerOptional.isPresent()){

                CartItems cart= new CartItems();

                cart.setProduct(optionalProduct.get());
                cart.setPrice(optionalProduct.get().getPrice());
                cart.setQuantity(1L);
                cart.setCustomer(customerOptional.get());
                cart.setOrder(activeOrder);

                CartItems updateCart = cartItemsRepo.save(cart);

                activeOrder.setTotalAmount(activeOrder.getAmount() + cart.getPrice());
                activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
                activeOrder.getCartItems().add(cart);

                orderRepo.save(activeOrder);


                return ResponseEntity.status(HttpStatus.CREATED).body(cart);


            }else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User or Product not found ");
            }
        }

    }

    @Override
    public OrderDto getCartByCustomerId(Long customerId){
        Order activeOrder = orderRepo.findByCustomerIdAndOrderStatus(customerId, OrderStatus.Pending);

        List<CartItemsDto> cartItemsDtoList  = activeOrder.getCartItems().stream().map(CartItems::getCartDto).collect(Collectors.toList());
        OrderDto orderDto = new OrderDto();
        orderDto.setAmount(activeOrder.getAmount());
        orderDto.setId(activeOrder.getId());
        orderDto.setOrderStatus(activeOrder.getOrderStatus());
        orderDto.setDiscount(activeOrder.getDiscount());
        orderDto.setTotalAmount(activeOrder.getTotalAmount());

        orderDto.setCartItems(cartItemsDtoList);

        if (activeOrder.getCoupon() != null){
            orderDto.setCouponName(activeOrder.getCoupon().getName());
        }

        return orderDto;
    }

    @Override
    public OrderDto ApplyCoupon(Long customerId,String code){
        Order activeOrder = orderRepo.findByCustomerIdAndOrderStatus(customerId, OrderStatus.Pending);
        Coupon coupon = couponRepo.findByCode(code).orElseThrow(()->new ValidationException("Coupon not Found"));
    if(couponIsExpired(coupon)){
        throw new ValidationException("Coupon has Expired");
    }

    double discountAmount = ((coupon.getDiscount()/100.0) * activeOrder.getTotalAmount());
    double netAmount = activeOrder.getTotalAmount() - discountAmount;

    activeOrder.setAmount((long) netAmount);
    activeOrder.setDiscount((long) discountAmount);
    activeOrder.setCoupon(coupon);

    orderRepo.save(activeOrder);

    return activeOrder.getOrderDto();


    }

    private boolean couponIsExpired(Coupon coupon){
        Date currentDate = new Date();
        Date expirationDate = coupon.getExpirationDate();

        return  expirationDate != null && currentDate.after(expirationDate);
    }

    @Override
    public OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto){
        Order activeOrder = orderRepo.findByCustomerIdAndOrderStatus(addProductInCartDto.getCustomerId(), OrderStatus.Pending);
        Optional<Product> optionalProduct = productRepo.findById(addProductInCartDto.getProductId());

        Optional<CartItems> optionalCartItems = cartItemsRepo.findByProductIdAndOrderIdAndCustomerId(
                addProductInCartDto.getProductId(),activeOrder.getId(),addProductInCartDto.getCustomerId()
        );

        if (optionalProduct.isPresent() && optionalCartItems.isPresent()){

            CartItems cartItems = optionalCartItems.get();
            Product product = optionalProduct.get();

            activeOrder.setAmount(activeOrder.getAmount() + product.getPrice() );
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() +product.getPrice());

            cartItems.setQuantity(cartItems.getQuantity() + 1);

            if (activeOrder.getCoupon() != null){

                double discountAmount = ((activeOrder.getCoupon().getDiscount()/100.0) * activeOrder.getTotalAmount());
                double netAmount = activeOrder.getTotalAmount() - discountAmount;

                activeOrder.setAmount((long) netAmount);
                activeOrder.setDiscount((long) discountAmount);

            }

            cartItemsRepo.save(cartItems);
            orderRepo.save(activeOrder);
            return activeOrder.getOrderDto();
        }
        return  null;
    }



    @Override
    public OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto){
        Order activeOrder = orderRepo.findByCustomerIdAndOrderStatus(addProductInCartDto.getCustomerId(), OrderStatus.Pending);
        Optional<Product> optionalProduct = productRepo.findById(addProductInCartDto.getProductId());

        Optional<CartItems> optionalCartItems = cartItemsRepo.findByProductIdAndOrderIdAndCustomerId(
                addProductInCartDto.getProductId(),activeOrder.getId(),addProductInCartDto.getCustomerId()
        );

        if (optionalProduct.isPresent() && optionalCartItems.isPresent()){

            CartItems cartItems = optionalCartItems.get();
            Product product = optionalProduct.get();

            activeOrder.setAmount(activeOrder.getAmount() - product.getPrice() );
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() - product.getPrice());

            cartItems.setQuantity(cartItems.getQuantity() - 1);

            if (activeOrder.getCoupon() != null){

                double discountAmount = ((activeOrder.getCoupon().getDiscount()/100.0) * activeOrder.getTotalAmount());
                double netAmount = activeOrder.getTotalAmount() - discountAmount;

                activeOrder.setAmount((long) netAmount);
                activeOrder.setDiscount((long) discountAmount);

            }

            cartItemsRepo.save(cartItems);
            orderRepo.save(activeOrder);
            return activeOrder.getOrderDto();
        }
        return  null;
    }


    @Override
    public OrderDto placeOrder(PlaceOrder placeOrder){
        Order activeOrder = orderRepo.findByCustomerIdAndOrderStatus(placeOrder.getCustomerId(), OrderStatus.Pending);
        Optional<Customer> optionalCustomer = customerRepo.findById(placeOrder.getCustomerId());

        if (optionalCustomer.isPresent()){
            activeOrder.setOrderDescription(placeOrder.getOrderDescription());
            activeOrder.setAddress(placeOrder.getAddress());
            activeOrder.setDate(new Date());
            activeOrder.setOrderStatus(OrderStatus.Placed);
            activeOrder.setTrackingId(UUID.randomUUID());

            orderRepo.save(activeOrder);

            ///////////////Order ////////////////////
            Order order = new Order();
            order.setAmount(0L);
            order.setTotalAmount(0L);
            order.setDiscount(0L);
            order.setCustomer(optionalCustomer.get());
            order.setOrderStatus(OrderStatus.Pending);
            orderRepo.save(order);


            return activeOrder.getOrderDto();
        }
        return null;
    }
}
