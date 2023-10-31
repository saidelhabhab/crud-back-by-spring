package com.tp.CRUD.response;

import com.tp.CRUD.entity.User;
import com.tp.CRUD.entity.User_jwt;
import lombok.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponse {

     private User_jwt user;
     private String jwtToken;

}
