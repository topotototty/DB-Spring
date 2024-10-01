package com.example.Pract3.controllers;

import com.example.Pract3.models.ProductModel;
import com.example.Pract3.models.CategoryModel;
import com.example.Pract3.service.ProductService;
import com.example.Pract3.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/create")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new ProductModel());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "createProduct";
    }

    @PostMapping
    public String createProduct(@Valid @ModelAttribute("product") ProductModel product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<CategoryModel> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "createProduct";
        }
        productService.createProduct(product);
        return "redirect:/products";
    }


    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ProductModel product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        List<CategoryModel> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "editProduct";
    }

    @PostMapping("/{id}/update")
    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute("product") ProductModel product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<CategoryModel> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "editProduct";
        }
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    // Метод для подтверждения удаления продукта
    @GetMapping("/{id}/delete")
    public String showDeleteConfirmation(@PathVariable Long id, Model model) {
        ProductModel product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        return "deleteProduct";
    }

    // Метод для удаления продукта
    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
