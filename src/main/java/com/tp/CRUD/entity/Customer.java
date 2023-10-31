package com.tp.CRUD.entity;


import com.tp.CRUD.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "costumer")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String password;
    private UserRole role;
    @Lob
    private byte[] img;
}
