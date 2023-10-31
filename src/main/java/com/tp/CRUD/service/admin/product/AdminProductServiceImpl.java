package com.tp.CRUD.service.admin.product;

import com.tp.CRUD.entity.Category;
import com.tp.CRUD.entity.Product;
import com.tp.CRUD.repository.CategoryRepo;
import com.tp.CRUD.repository.ProductRepo;
import com.tp.CRUD.request.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService{

    private final ProductRepo productRepo;

    private final CategoryRepo categoryRepo;


    @Override
    public ProductDto addProduct(ProductDto productDto) throws IOException {

        Product product = new Product();

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImg(productDto.getImg().getBytes());

        Category category = categoryRepo.findById(productDto.getCategoryId()).orElseThrow();

        product.setCategory(category);

        return productRepo.save(product).getDto();
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepo.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }
}
