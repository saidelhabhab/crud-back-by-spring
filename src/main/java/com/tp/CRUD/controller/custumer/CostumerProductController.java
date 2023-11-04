package com.tp.CRUD.controller.custumer;

import com.tp.CRUD.request.ProductDto;
import com.tp.CRUD.service.admin.product.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/costumer/")
public class CostumerProductController {

    @Autowired
    private AdminProductService adminProductService;


    @GetMapping("products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDto = adminProductService.getAllProduct();
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("search/{name}")
    public  ResponseEntity<List<ProductDto>> searchProductByTitle(@PathVariable String name){
        List<ProductDto> productDto = adminProductService.getAllProductByName(name);
        return ResponseEntity.ok(productDto);
    }
}
