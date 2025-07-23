package com.ornek.restorant.restorantapp.service.ServiceImpl;

import com.ornek.restorant.restorantapp.exception.MenuItemNotFoundException;
import com.ornek.restorant.restorantapp.exception.OrderItemNotFoundException;
import com.ornek.restorant.restorantapp.exception.OrderNotFoundException;
import com.ornek.restorant.restorantapp.model.converter.OrderItemConverter;
import com.ornek.restorant.restorantapp.model.dto.OrderItemDto;
import com.ornek.restorant.restorantapp.model.entity.MenuItem;
import com.ornek.restorant.restorantapp.model.entity.Order;
import com.ornek.restorant.restorantapp.model.entity.OrderItem;
import com.ornek.restorant.restorantapp.repository.MenuItemRepository;
import com.ornek.restorant.restorantapp.repository.OrderItemRepository;
import com.ornek.restorant.restorantapp.repository.OrderRepository;
import com.ornek.restorant.restorantapp.service.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderRepository orderRepository;


    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, MenuItemRepository menuItemRepository, OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderRepository = orderRepository;
    }
    @Override
    public List<OrderItemDto> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems.stream()
                .map(OrderItemConverter::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public OrderItemDto getOrderItemById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new OrderItemNotFoundException("OrderItem not found with id: " + id));
        return OrderItemConverter.toDto(orderItem);
    }

    @Override
    public OrderItem createOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setPrice(orderItemDto.getPrice());

        // MenuItem DB'den çekiliyor, yoksa hata fırlatılıyor
        MenuItem menuItem = menuItemRepository.findById(orderItemDto.getMenuItemId())
                .orElseThrow(() -> new MenuItemNotFoundException("MenuItem bulunamadı: " + orderItemDto.getMenuItemId()));

        orderItem.setMenuitem(menuItem);

        // Aynı şekilde Order için de
        Order order = orderRepository.findById(orderItemDto.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Order bulunamadı: " + orderItemDto.getOrderId()));

        orderItem.setOrder(order);

        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItemDto updateOrderItem(Long id, OrderItemDto orderItemDto) {
        OrderItem existingOrderItem = orderItemRepository.findById(orderItemDto.getId())
                .orElseThrow(() -> new OrderItemNotFoundException("OrderItem not found with id: " + orderItemDto.getId()));

        existingOrderItem.setQuantity(orderItemDto.getQuantity());
        existingOrderItem.setPrice(orderItemDto.getPrice());
        // Diğer alanlar varsa onları da güncelle

        OrderItem updatedOrderItem = orderItemRepository.save(existingOrderItem);
        return OrderItemConverter.toDto(updatedOrderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        OrderItem existingOrderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new OrderItemNotFoundException("OrderItem not found with id: " + id));
        orderItemRepository.delete(existingOrderItem);

    }
}
