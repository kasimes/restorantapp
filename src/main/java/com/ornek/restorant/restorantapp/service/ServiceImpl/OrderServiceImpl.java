package com.ornek.restorant.restorantapp.service.ServiceImpl;

import com.ornek.restorant.restorantapp.exception.BranchNotFoundException;
import com.ornek.restorant.restorantapp.exception.CustomerNotFoundException;
import com.ornek.restorant.restorantapp.exception.OrderNotFoundException;
import com.ornek.restorant.restorantapp.model.converter.OrderConverter;
import com.ornek.restorant.restorantapp.model.dto.OrderDto;
import com.ornek.restorant.restorantapp.model.entity.Branch;
import com.ornek.restorant.restorantapp.model.entity.Users;
import com.ornek.restorant.restorantapp.model.entity.Order;
import com.ornek.restorant.restorantapp.model.entity.Restaurant;
import com.ornek.restorant.restorantapp.repository.BranchRepository;
import com.ornek.restorant.restorantapp.repository.UsersRepository;
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
    private final UsersRepository usersRepository;
    private final BranchRepository branchRepository;
    

    public OrderServiceImpl(OrderRepository orderRepository, OrderConverter orderConverter, UsersRepository usersRepository, RestaurantRepository restaurantRepository, BranchRepository branchRepository) {
        this.orderRepository = orderRepository;
        this.orderConverter = orderConverter;
        this.usersRepository = usersRepository;
        this.branchRepository = branchRepository;

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
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
        return orderConverter.toDto(order);
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Users users = usersRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + orderDto.getCustomerId()));

        Branch branch = branchRepository.findById(orderDto.getBranchId())
                .orElseThrow(() -> new BranchNotFoundException("Branch not found"));

        Order order = OrderConverter.toEntity(orderDto,users,branch);
        order = orderRepository.save(order);


        return orderConverter.toDto(order);
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));

        Users users = usersRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + orderDto.getCustomerId()));

        Branch branch = branchRepository.findById(orderDto.getBranchId())
                .orElseThrow(() -> new BranchNotFoundException("Branch not found"));

        order.setUsers(users);
        order.setBranch(branch);
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setOrderTime(orderDto.getOrderTime());
        order.setTotalPrice(orderDto.getTotalPrice());

        Order updatedOrder = orderRepository.save(order);
        return orderConverter.toDto(updatedOrder);

    }



}
