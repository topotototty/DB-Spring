package com.example.Pract3.service;

import com.example.Pract3.models.ProductModel;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductModel> getAllProducts();
    Optional<ProductModel> getProductById(Long id);
    ProductModel createProduct(ProductModel product);
    ProductModel updateProduct(Long id, ProductModel product);
    void deleteProduct(Long id);
}
