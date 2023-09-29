package com.tp.CRUD.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String phone;
    private Date date;

    @Lob
    private byte[] photo;

    @Lob
    private byte[] video;

    public User(String username, String email,String phone, Date date,byte[] photo,byte[] video) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.date = date;
        this.photo = photo;
        this.video = video;
    }
}
