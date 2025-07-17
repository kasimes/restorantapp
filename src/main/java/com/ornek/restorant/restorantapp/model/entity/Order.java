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
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name="customer_id")
    private Users users;



    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;


    @Enumerated(EnumType.STRING)
    @Column(name = "order_status",insertable = false,nullable = false)
    private OrderStatus orderStatus;

    private LocalDateTime orderTime;

    private Double totalPrice;


}
