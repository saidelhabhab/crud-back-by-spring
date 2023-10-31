package com.tp.CRUD.service.admin.product;

import com.tp.CRUD.request.ProductDto;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {

    public ProductDto addProduct(ProductDto productDto) throws IOException;

    public List<ProductDto> getAllProduct();
}
