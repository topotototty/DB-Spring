package com.example.Pract3.service;

import com.example.Pract3.models.CategoryModel;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryModel> getAllCategories();
    Optional<CategoryModel> getCategoryById(Long id);
    CategoryModel createCategory(CategoryModel category);
    CategoryModel updateCategory(Long id, CategoryModel category);
    void deleteCategory(Long id);
}
