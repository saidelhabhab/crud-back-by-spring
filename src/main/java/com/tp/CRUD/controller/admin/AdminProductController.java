package com.tp.CRUD.controller.admin;

import com.tp.CRUD.request.FAQDto;
import com.tp.CRUD.request.ProductDto;
import com.tp.CRUD.service.admin.faq.FAQService;
import com.tp.CRUD.service.admin.product.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/admin/")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService adminProductService;

    private final FAQService faqService;

    @PostMapping("product")
    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws IOException {

        ProductDto productDto1 = adminProductService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
    }

    @GetMapping("products")
    public  ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDto = adminProductService.getAllProduct();
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("search/{name}")
    public  ResponseEntity<List<ProductDto>> getAllProductByName(@PathVariable String name){
        List<ProductDto> productDto = adminProductService.getAllProductByName(name);
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("product/{productId}")
    public  ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
       boolean delete = adminProductService.deleteProduct(productId);
       if (delete) {
           return ResponseEntity.noContent().build();
       }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("faq/{productId}")
    public ResponseEntity<FAQDto> addFAQ(@PathVariable Long productId, @RequestBody FAQDto faqDto)  {

        return ResponseEntity.status(HttpStatus.CREATED).body(faqService.postFAQ(productId,faqDto));
    }

    @GetMapping("product/{productId}")
    public  ResponseEntity<ProductDto> getProductById(@PathVariable Long productId){
        ProductDto productDto = adminProductService.getProductById(productId);
        if(productDto != null){
            return  ResponseEntity.ok(productDto);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("product/{productId}")
    public  ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId,@ModelAttribute ProductDto productDto) throws IOException{
        ProductDto updatedProduct = adminProductService.updateProduct(productId,productDto);

        if(updatedProduct != null){
            return  ResponseEntity.ok(updatedProduct);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
