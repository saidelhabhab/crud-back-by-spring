package com.tp.CRUD.request;

import com.tp.CRUD.entity.Customer;
import com.tp.CRUD.entity.Product;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReviewDto {

    private Long id;

    private Long rating;

    private String description;

    private MultipartFile img;

    private byte[] returnedImg;

    private Long customerId;

    private Long productId;

    private String username;
}
