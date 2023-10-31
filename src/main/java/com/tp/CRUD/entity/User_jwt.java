package com.tp.CRUD.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "user_jwt")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User_jwt {

    @Id
    private String username;
    private String fullName;
    private String password;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
                joinColumns = {
                    @JoinColumn(name = "USER_ID")
                },
                inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
                })
    private Set<Role> role;

}
