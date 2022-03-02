package com.example.product.service;

import com.example.product.pojo.ProductVO;

import java.util.List;


public interface ProductService {
    ProductVO addProduct(ProductVO productVO);

    List<ProductVO> getProducts();

    ProductVO getProductById(Long id);

    ProductVO updateProduct(Long id, ProductVO productVO);

    void deleteProduct(Long id);
}
