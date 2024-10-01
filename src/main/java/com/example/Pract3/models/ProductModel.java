package com.example.Pract3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название продукта обязательно для заполнения")
    @Size(min = 3, max = 100, message = "Название продукта должно содержать от 3 до 100 символов")
    private String name;

    @Min(value = 1, message = "Цена продукта должна быть не меньше 0")
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @NotNull(message = "Укажите категорию продукта")
    private CategoryModel category;

    @Override
    public String toString() {
        return "ProductModel{id=" + id + ", name='" + name + '\'' + ", price=" + price + '}';
    }
}
