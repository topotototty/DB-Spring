package com.example.Pract3.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя обязательно для заполнения")
    @Size(min = 2, max = 30, message = "Имя должно содержать от 2 до 30 символов")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна для заполнения")
    @Size(min = 2, max = 30, message = "Фамилия должна содержать от 2 до 30 символов")
    private String secondName;

    @NotBlank(message = "Адрес обязателен для заполнения")
    @Size(min = 10, max = 100, message = "Адрес должен содержать от 10 до 100 символов")
    private String address;

}
