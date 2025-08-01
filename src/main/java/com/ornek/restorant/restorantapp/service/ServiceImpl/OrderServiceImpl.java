package com.ornek.restorant.restorantapp.service.ServiceImpl;

import com.ornek.restorant.restorantapp.exception.notfound.BranchNotFoundException;
import com.ornek.restorant.restorantapp.exception.notfound.CustomerNotFoundException;
import com.ornek.restorant.restorantapp.exception.notfound.OrderNotFoundException;
import com.ornek.restorant.restorantapp.model.converter.OrderConverter;
import com.ornek.restorant.restorantapp.model.dto.OrderDto;
import com.ornek.restorant.restorantapp.model.dto.OrderItemDto;
import com.ornek.restorant.restorantapp.model.entity.Branch;
import com.ornek.restorant.restorantapp.model.entity.Users;
import com.ornek.restorant.restorantapp.model.entity.Order;
import com.ornek.restorant.restorantapp.repository.BranchRepository;
import com.ornek.restorant.restorantapp.repository.UsersRepository;
import com.ornek.restorant.restorantapp.repository.OrderRepository;
import com.ornek.restorant.restorantapp.service.OrderService;
import com.ornek.restorant.restorantapp.service.strategy.EsikIndirim;
import com.ornek.restorant.restorantapp.service.strategy.YuzdeIndirim;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private  final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final UsersRepository usersRepository;
    private final BranchRepository branchRepository;
    private final YuzdeIndirim yuzdeIndirim;
    private final EsikIndirim esikIndirim;


    public OrderServiceImpl(OrderRepository orderRepository, OrderConverter orderConverter, UsersRepository usersRepository, BranchRepository branchRepository, YuzdeIndirim yuzdeIndirim, EsikIndirim esikIndirim) {
        this.orderRepository = orderRepository;
        this.orderConverter = orderConverter;
        this.usersRepository = usersRepository;
        this.branchRepository = branchRepository;
        this.yuzdeIndirim = yuzdeIndirim;
        this.esikIndirim = esikIndirim;
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
    public OrderDto createOrder(OrderDto orderDto,List<OrderItemDto> orderItemDto) {
        Users users = usersRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + orderDto.getCustomerId()));

        Branch branch = branchRepository.findById(orderDto.getBranchId())
                .orElseThrow(() -> new BranchNotFoundException("Branch not found"));

        //total tutar hesaplama
        BigDecimal totalAmount = orderItemDto.stream()
                .map(item ->BigDecimal.valueOf(item.getPrice())
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //minumun satıs tutarı kontorlu
        if (branch.getMinimumOrderAmount() !=null && totalAmount.compareTo(branch.getMinimumOrderAmount()) < 0) {
            throw new RuntimeException("Minimum Order Amount is greater than Minimum Order Amount"+branch.getMinimumOrderAmount()+"Tl dir");
        }

        // şube açık kapalımı ona bakar
        LocalTime now = LocalTime.now();
        if (branch.getOpeningTime() != null && branch.getClosingTime() != null) {
            if (now.isBefore(branch.getOpeningTime()) || now.isAfter(branch.getClosingTime())) {
                throw new RuntimeException("Şube şu anda kapalı. Lütfen açılış saatleri arasında sipariş veriniz.");
            }
        }

        //indirim stratejisi
        BigDecimal finalTotalAmount = totalAmount;
        if(orderDto.getDiscountType() != null) {
            finalTotalAmount = switch (orderDto.getDiscountType()) {
                case "YUZDE" -> yuzdeIndirim.indirimUygula(totalAmount);
                case "ESIK" -> esikIndirim.indirimUygula(totalAmount);
                default -> totalAmount;
            };
        }
        orderDto.setTotalPrice(finalTotalAmount.doubleValue());

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


    @Override
    public void deleteOrderById(long id) {
       Order order=orderRepository.findById(id)
               .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
       orderRepository.delete(order);




    }




}
