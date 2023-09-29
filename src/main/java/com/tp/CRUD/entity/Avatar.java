package com.tp.CRUD.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "avatars")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Avatar {


    @Id
    @Column(name = "_id")
    private Long id;

    //...


    @Lob
    private byte[] avatar;
}
