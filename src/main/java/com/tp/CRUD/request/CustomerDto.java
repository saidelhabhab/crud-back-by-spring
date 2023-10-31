package com.tp.CRUD.request;


import com.tp.CRUD.enums.UserRole;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {

    private Long id;
    private String email;
    private String name;
    private UserRole role;
}
