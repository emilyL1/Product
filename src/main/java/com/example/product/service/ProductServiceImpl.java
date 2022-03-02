package com.example.product.service;

import com.example.product.entity.Product;
import com.example.product.pojo.ProductVO;
import com.example.product.repository.ProductRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;

    HashMap<Long, Product> products = new HashMap<>();
    @Override
    public ProductVO addProduct(ProductVO productVO) {
        Product product = new Product();
        BeanUtils.copyProperties(productVO,product);
        //without using database
//        products.put(product.getId(),product);
//        products.values().stream().forEach(System.out::println);
        //using database
        Product productSaved = productRepo.save(product);
        ProductVO res = new ProductVO();
        BeanUtils.copyProperties(productSaved,res);
        return res;
    }

    @Override
    public List<ProductVO> getProducts() {
        List<ProductVO> res = new ArrayList<>();
        List<Product> products = productRepo.getProducts();
        products.forEach(p ->{
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(p,productVO);
            res.add(productVO);
        });
        return res;
    }

    @Override
    public ProductVO getProductById(Long id) {
        ProductVO res = new ProductVO();
        Product product = productRepo.getProductById(id);
        if(product == null) throw new EntityNotFoundException("Product doesn't exist");
        BeanUtils.copyProperties(product,res);
        return res;
    }

    @Override
    public ProductVO updateProduct(Long id, ProductVO productVO) {
        ProductVO res = new ProductVO();
        Product p = productRepo.getProductById(id);
        if(p == null) throw new EntityNotFoundException("Product doesn't exist");
        p = new Product(id,productVO.getName(),productVO.getPrice());
        Product product = productRepo.updateProduct(p);
        BeanUtils.copyProperties(product,res);
        return res;
    }

    @Override
    public void deleteProduct(Long id) {
        Product p = productRepo.getProductById(id);
        if(p == null) throw new EntityNotFoundException("Product doesn't exist");
        productRepo.deleteProduct(p);
        return;
    }
}
