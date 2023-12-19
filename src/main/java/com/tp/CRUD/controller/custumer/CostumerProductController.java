package com.tp.CRUD.controller.custumer;

import com.tp.CRUD.request.ProductDetailDto;
import com.tp.CRUD.request.ProductDto;
import com.tp.CRUD.service.admin.product.AdminProductService;
import com.tp.CRUD.service.costumer.CustomerProductService;
import com.tp.CRUD.service.costumer.CustomerProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer/")
public class CostumerProductController {

    @Autowired
    private CustomerProductService customerProductService;


    @GetMapping("products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDto = customerProductService.getAllProduct();
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("search/{name}")
    public  ResponseEntity<List<ProductDto>> searchProductByTitle(@PathVariable String name){
        List<ProductDto> productDto = customerProductService.searchProductByTitle(name);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("product/{productId}")
    public ResponseEntity<ProductDetailDto> getProductDetailsById(@PathVariable Long productId){
        ProductDetailDto productDetailDto = customerProductService.getProductDetailsById(productId);

        if (productDetailDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDetailDto);
    }
}
