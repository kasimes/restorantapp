package com.ornek.restorant.restorantapp.model.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "menu")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Örn: Kahvaltı Menüsü

    private String description;



    @ManyToOne
    @JoinColumn(name = "branch_id" , nullable = false)
    private Branch branch;


}
