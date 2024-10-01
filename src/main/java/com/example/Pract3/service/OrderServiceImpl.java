package com.example.Pract3.service;

import com.example.Pract3.models.OrderModel;
import com.example.Pract3.repositories.OrderRepository;
import com.example.Pract3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderModel> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<OrderModel> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void createOrder(OrderModel order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
    @Override
    public void updateOrder(Long id, OrderModel order) {
        order.setId(id);
        orderRepository.save(order);
    }


}
