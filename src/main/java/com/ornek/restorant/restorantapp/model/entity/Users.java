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

    @ManyToOne
    @JoinColumn(name = "address_id")  // FK oluşturur
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Column(nullable = false)
    private String password;


}
