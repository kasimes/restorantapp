package com.ornek.restorant.restorantapp.entity;
import com.ornek.restorant.restorantapp.enums.AvailabilityStatus;
import com.ornek.restorant.restorantapp.enums.MenuitemCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "Menu_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menuitem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private String category;

    @Column(name="is_available")
    private boolean isAvailable;



    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;



    @Enumerated(EnumType.STRING)
    @Column(name = "order_status",nullable = false)
    private AvailabilityStatus  availabilityStatus;





}
