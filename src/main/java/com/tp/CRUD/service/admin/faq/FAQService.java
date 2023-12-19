package com.tp.CRUD.service.admin.faq;

import com.tp.CRUD.request.FAQDto;

public interface FAQService {

    public FAQDto postFAQ(Long productId, FAQDto faqDto);
}
