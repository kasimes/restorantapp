package com.ornek.restorant.restorantapp.model.entity;

import com.ornek.restorant.restorantapp.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;


}
