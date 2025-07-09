package com.ornek.restorant.restorantapp.entity;
import com.ornek.restorant.restorantapp.Enums.AvailabilityStatus;
import com.ornek.restorant.restorantapp.Enums.MenuitemCategory;
import com.ornek.restorant.restorantapp.Enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import  com.ornek.restorant.restorantapp.entity.Restaurant;
import jakarta.persistence.*;

@Entity
@Table(name = "MenuItem")
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
    @JoinColumn(name = "restaurant_id",nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private MenuitemCategory menuitemCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status",nullable = false)
    private AvailabilityStatus  availabilityStatus;





}
