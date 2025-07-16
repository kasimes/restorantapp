package com.ornek.restorant.restorantapp.service.ServiceImpl;

import com.ornek.restorant.restorantapp.model.converter.OrderConverter;
import com.ornek.restorant.restorantapp.model.dto.OrderDto;
import com.ornek.restorant.restorantapp.model.entity.Customer;
import com.ornek.restorant.restorantapp.model.entity.Order;
import com.ornek.restorant.restorantapp.model.entity.Restaurant;
import com.ornek.restorant.restorantapp.repository.CustomerRepository;
import com.ornek.restorant.restorantapp.repository.OrderRepository;
import com.ornek.restorant.restorantapp.repository.RestaurantRepository;
import com.ornek.restorant.restorantapp.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private  final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final CustomerRepository  customerRepository;
    private final RestaurantRepository restaurantRepository;
    

    public OrderServiceImpl(OrderRepository orderRepository, OrderConverter orderConverter, CustomerRepository customerRepository, RestaurantRepository restaurantRepository) {
        this.orderRepository = orderRepository;
        this.orderConverter = orderConverter;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<OrderDto> getAllOrders() {

        return  orderRepository.findAll().stream()
                .map(orderConverter::toDto)
                .collect(Collectors.toList());

    }
    @Override
    public OrderDto getOrderById(long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        return orderConverter.toDto(order);
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Customer customer = customerRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + orderDto.getCustomerId()));

        Restaurant restaurant = restaurantRepository.findById(orderDto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + orderDto.getRestaurantId()));

        Order order = OrderConverter.toEntity(orderDto, customer, restaurant);
        Order savedOrder = orderRepository.save(order);

        return orderConverter.toDto(savedOrder);
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        Customer customer = customerRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + orderDto.getCustomerId()));

        Restaurant restaurant = restaurantRepository.findById(orderDto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + orderDto.getRestaurantId()));

        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setOrderTime(orderDto.getOrderTime());
        order.setTotalPrice(orderDto.getTotalPrice());

        Order updatedOrder = orderRepository.save(order);
        return orderConverter.toDto(updatedOrder);

    }



}
