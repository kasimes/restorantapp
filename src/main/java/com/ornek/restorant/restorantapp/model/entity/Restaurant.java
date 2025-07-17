package com.ornek.restorant.restorantapp.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private Long phone;

    private String email;






    @OneToMany(mappedBy = "restaurant")
    private List<Branch> branches;

}
