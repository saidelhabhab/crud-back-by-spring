package com.tp.CRUD.service.costumer.wishlist;


import com.tp.CRUD.entity.Customer;
import com.tp.CRUD.entity.Product;
import com.tp.CRUD.entity.Wishlist;
import com.tp.CRUD.repository.CustomerRepo;
import com.tp.CRUD.repository.ProductRepo;
import com.tp.CRUD.repository.WishlistRepo;
import com.tp.CRUD.request.WishlistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService{

    private  final CustomerRepo customerRepo;

    private  final ProductRepo productRepo;

    private  final WishlistRepo wishlistRepo;



    @Override
    public WishlistDto addProductToWishlist(WishlistDto wishlistDto){
        Optional<Product> optionalProduct = productRepo.findById(wishlistDto.getProductId());
        Optional<Customer> optionalCustomer = customerRepo.findById(wishlistDto.getCustomerId());

        if (optionalProduct.isPresent() && optionalCustomer.isPresent()){

            Wishlist wishlist = new Wishlist();

            wishlist.setProduct(optionalProduct.get());
            wishlist.setCustomer(optionalCustomer.get());

            return wishlistRepo.save(wishlist).getWishlistDto();

        }else{
            return null;
        }
    }


    @Override
    public List<WishlistDto>  getWishlistByCustomerId(Long customerId){
        return wishlistRepo.findAllByCustomerId(customerId).stream().map(Wishlist::getWishlistDto).collect(Collectors.toList());
    }
}
