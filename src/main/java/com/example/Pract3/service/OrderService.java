package com.example.Pract3.service;

import com.example.Pract3.models.OrderModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    List<OrderModel> getAllOrders();
    Optional<OrderModel> getOrderById(Long id);
    void createOrder(OrderModel order);
    void updateOrder(Long id, OrderModel order);
    void deleteOrder(Long id);
}
