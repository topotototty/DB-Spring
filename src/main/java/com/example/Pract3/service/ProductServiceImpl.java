package com.example.Pract3.service;

import com.example.Pract3.models.ProductModel;
import com.example.Pract3.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<ProductModel> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public ProductModel createProduct(ProductModel product) {
        return productRepository.save(product);
    }

    @Override
    public ProductModel updateProduct(Long id, ProductModel updatedProduct) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setPrice(updatedProduct.getPrice());
                    product.setCategory(updatedProduct.getCategory());
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + id + " not found"));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
