package com.example.Pract3.service;

import com.example.Pract3.models.CategoryModel;
import com.example.Pract3.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<CategoryModel> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public CategoryModel createCategory(CategoryModel category) {
        return categoryRepository.save(category);
    }

    @Override
    public CategoryModel updateCategory(Long id, CategoryModel updatedCategory) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(updatedCategory.getName());
                    return categoryRepository.save(category);
                })
                .orElseThrow(() -> new IllegalArgumentException("Category with ID " + id + " not found"));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
