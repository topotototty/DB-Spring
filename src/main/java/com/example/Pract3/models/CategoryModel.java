package com.example.Pract3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название категории обязательно для заполнения")
    @Size(min = 3, max = 50, message = "Название категории должно содержать от 3 до 50 символов")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductModel> products;

    @Override
    public String toString() {
        return "CategoryModel{id=" + id + ", name='" + name + '\'' + '}';
    }
}
