package com.tp.CRUD.service.admin.faq;


import com.tp.CRUD.entity.FAQ;
import com.tp.CRUD.entity.Product;
import com.tp.CRUD.repository.FAQRepo;
import com.tp.CRUD.repository.ProductRepo;
import com.tp.CRUD.request.FAQDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements  FAQService{

    private  final FAQRepo faqRepo;

    private final ProductRepo productRepo;


    @Override
    public FAQDto postFAQ(Long productId, FAQDto faqDto){

        Optional<Product> optionalProduct = productRepo.findById(productId);

        if (optionalProduct.isPresent()){

            FAQ faq = new FAQ();

            faq.setQuestion(faqDto.getQuestion());
            faq.setAnswer(faqDto.getAnswer());
            faq.setProduct(optionalProduct.get());

            return  faqRepo.save(faq).getFAQDto();
        }

        return null;
    }
}
