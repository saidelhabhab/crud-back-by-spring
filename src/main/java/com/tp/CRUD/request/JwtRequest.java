package com.tp.CRUD.request;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtRequest {

    private String username;
    private String password;
}
