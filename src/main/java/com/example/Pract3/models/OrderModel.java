package com.example.Pract3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotNull(message = "Покупатель обязателен для заполнения")
    private UserModel customer;

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductModel> products = new HashSet<>();

    public double getTotalPrice() {
        return products.stream()
                .mapToDouble(ProductModel::getPrice)
                .sum();
    }

    @Override
    public String toString() {
        return "OrderModel{id=" + id + "" +
                ", customer=" + customer.getName() +
                ", totalPrice=" + getTotalPrice() + '}';
    }
}
