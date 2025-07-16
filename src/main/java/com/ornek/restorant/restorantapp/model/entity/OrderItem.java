package com.ornek.restorant.restorantapp.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "OrderItem")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn( name = "order_id")
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuitem;

    private  Integer quantity;
    private  double price;
}
