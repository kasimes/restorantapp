package com.ornek.restorant.restorantapp.model.entity;

import com.ornek.restorant.restorantapp.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant  ;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status",insertable = false,nullable = false)
    private OrderStatus orderStatus;

    private LocalDateTime orderTime;

    private Double totalPrice;

    @PrePersist
    protected void onCreate() {
        if (orderTime == null) {
            orderTime = LocalDateTime.now();
        }
    }
}
