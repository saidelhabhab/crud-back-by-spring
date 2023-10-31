package com.tp.CRUD.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "role")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {

    @Id
    private String roleName;
    private String roleDescription;

}
