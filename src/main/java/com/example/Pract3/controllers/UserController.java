package com.example.Pract3.controllers;

import com.example.Pract3.models.UserModel;
import com.example.Pract3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new UserModel());
        return "createUser";
    }

    @PostMapping
    public String createUser(@Valid @ModelAttribute("user") UserModel user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "createUser";
        }
        userService.createUser(user);
        return "redirect:/users";
    }

    // Метод для отображения формы редактирования
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        UserModel user = userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "editUser";
    }

    // Метод для обработки редактирования пользователя
    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable Long id, @Valid @ModelAttribute("user") UserModel user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "editUser";
        }
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    // Метод для подтверждения удаления пользователя
    @GetMapping("/{id}/delete")
    public String showDeleteConfirmation(@PathVariable Long id, Model model) {
        UserModel user = userService.getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "deleteUser";
    }

    // Метод для удаления пользователя
    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
