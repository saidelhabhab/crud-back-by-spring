package com.tp.CRUD.service.costumer;

import com.tp.CRUD.entity.FAQ;
import com.tp.CRUD.entity.Product;
import com.tp.CRUD.entity.Review;
import com.tp.CRUD.repository.FAQRepo;
import com.tp.CRUD.repository.ProductRepo;
import com.tp.CRUD.repository.ReviewRepo;
import com.tp.CRUD.request.ProductDetailDto;
import com.tp.CRUD.request.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerProductServiceImpl implements CustomerProductService{

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private FAQRepo faqRepo;

    @Autowired
    private ReviewRepo reviewRepo;

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


    @Override
    public ProductDetailDto getProductDetailsById(Long productId){
        Optional<Product> optionalProduct = productRepo.findById(productId);

        if (optionalProduct.isPresent()){
            List<FAQ> faqList = faqRepo.findAllByProductId(productId);
            List<Review>  reviewList = reviewRepo.findAllByProductId(productId);

            ProductDetailDto productDetailDto = new ProductDetailDto();

            productDetailDto.setProductDto(optionalProduct.get().getDto());
            productDetailDto.setFaqDtoList(faqList.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));
            productDetailDto.setReviewDtoList(reviewList.stream().map(Review::getDto).collect(Collectors.toList()));

            return productDetailDto;
        }
        return  null;
    }
}
