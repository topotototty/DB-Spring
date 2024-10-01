package com.example.Pract3.controllers;

import com.example.Pract3.models.CategoryModel;
import com.example.Pract3.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new CategoryModel());
        return "createCategory";
    }

    @PostMapping
    public String createCategory(@Valid @ModelAttribute("category") CategoryModel category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "createCategory";
        }
        categoryService.createCategory(category);
        return "redirect:/categories";
    }

    // Метод для отображения формы редактирования
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        CategoryModel category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        model.addAttribute("category", category);
        return "editCategory";
    }

    // Метод для обработки редактирования категории
    @PostMapping("/{id}/update")
    public String updateCategory(@PathVariable Long id, @Valid @ModelAttribute("category") CategoryModel category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "editCategory";
        }
        categoryService.updateCategory(id, category);
        return "redirect:/categories";
    }

    // Метод для подтверждения удаления категории (с предупреждением)
    @GetMapping("/{id}/delete")
    public String showDeleteConfirmation(@PathVariable Long id, Model model) {
        CategoryModel category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        model.addAttribute("category", category);
        model.addAttribute("products", category.getProducts()); // Продукты, связанные с категорией
        return "deleteCategory";
    }

    // Метод для удаления категории и связанных продуктов
    @PostMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
