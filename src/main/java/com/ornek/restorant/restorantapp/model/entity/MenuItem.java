package com.ornek.restorant.restorantapp.model.entity;
import com.ornek.restorant.restorantapp.model.enums.AvailabilityStatus;
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
public class MenuItem {
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
