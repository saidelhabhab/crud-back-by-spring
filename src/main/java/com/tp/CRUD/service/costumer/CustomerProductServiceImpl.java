package com.tp.CRUD.service.costumer;

import com.tp.CRUD.entity.Product;
import com.tp.CRUD.repository.ProductRepo;
import com.tp.CRUD.request.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerProductServiceImpl implements CustomerProductService{

    @Autowired
    private ProductRepo productRepo;


    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepo.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchProductByTitle(String name) {

        List<Product> products = productRepo.findAllNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }
}
