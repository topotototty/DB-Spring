package com.example.Pract3.controllers;

import com.example.Pract3.models.OrderModel;
import com.example.Pract3.models.UserModel;
import com.example.Pract3.service.OrderService;
import com.example.Pract3.service.ProductService;
import com.example.Pract3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService, ProductService productService) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    @GetMapping("/create")
    public String showCreateOrderForm(Model model) {
        model.addAttribute("order", new OrderModel());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("products", productService.getAllProducts());
        return "createOrder";
    }

    @PostMapping
    public String createOrder(@Valid @ModelAttribute("order") OrderModel order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("products", productService.getAllProducts());
            return "createOrder";
        }
        orderService.createOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String showEditOrderForm(@PathVariable Long id, Model model) {
        OrderModel order = orderService.getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        model.addAttribute("order", order);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("products", productService.getAllProducts());
        return "editOrder";
    }

    @PostMapping("/{id}/update")
    public String updateOrder(@PathVariable Long id, @Valid @ModelAttribute("order") OrderModel order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("products", productService.getAllProducts());
            return "editOrder";
        }
        orderService.updateOrder(id, order);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/delete")
    public String showDeleteConfirmation(@PathVariable Long id, Model model) {
        OrderModel order = orderService.getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        model.addAttribute("order", order);
        return "deleteOrder";
    }

    @PostMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}
