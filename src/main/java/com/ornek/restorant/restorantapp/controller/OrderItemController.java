package com.ornek.restorant.restorantapp.controller;

import com.ornek.restorant.restorantapp.model.dto.OrderItemDto;
import com.ornek.restorant.restorantapp.model.entity.OrderItem;
import com.ornek.restorant.restorantapp.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<OrderItemDto>> getAllOrderItems() {
        List<OrderItemDto> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable Long id) {
        OrderItemDto orderItem = orderItemService.getOrderItemById(id);
        return ResponseEntity.ok(orderItem);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemDto orderItemDto) {
        OrderItem createdOrderItem = orderItemService.createOrderItem(orderItemDto);
        return ResponseEntity.ok(createdOrderItem);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDto> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemDto orderItemDto) {
        OrderItemDto updatedOrderItem = orderItemService.updateOrderItem(id, orderItemDto);
        return ResponseEntity.ok(updatedOrderItem);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }

}
