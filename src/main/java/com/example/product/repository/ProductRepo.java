package com.example.product.repository;

import com.example.product.entity.Product;

import java.util.List;

public interface ProductRepo {
    Product save(Product product);

    List<Product> getProducts();

    Product getProductById(Long id);

    Product updateProduct(Product product);

    void deleteProduct(Product p);
}
