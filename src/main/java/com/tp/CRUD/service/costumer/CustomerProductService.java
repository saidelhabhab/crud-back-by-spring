package com.tp.CRUD.service.costumer;


import com.tp.CRUD.request.ProductDto;

import java.util.List;


public interface CustomerProductService {

    public List<ProductDto> getAllProduct();


    public List<ProductDto> searchProductByTitle(String name);

}
