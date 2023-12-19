package com.tp.CRUD.service.admin.product;

import com.tp.CRUD.request.ProductDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AdminProductService {

    public ProductDto addProduct(ProductDto productDto) throws IOException;

    public List<ProductDto> getAllProduct();

    public List<ProductDto> getAllProductByName(String name);

    public boolean deleteProduct(Long id);

    public  ProductDto getProductById(Long productId);

    public  ProductDto updateProduct(Long productId,ProductDto productDto) throws IOException;
}
