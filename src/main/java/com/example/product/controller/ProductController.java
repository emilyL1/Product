package com.example.product.controller;

import com.example.product.entity.Product;
import com.example.product.pojo.ProductVO;
import com.example.product.service.ProductService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ProductVO> addProduct(
            @Validated @RequestBody ProductVO productVO){
        ProductVO product = productService.addProduct(productVO);
        log.info("Create Successfully");
        return new ResponseEntity(product, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProductVO>> getProducts(){
        List<ProductVO> products = productService.getProducts();
        return new ResponseEntity(products,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductVO> getProductById(@PathVariable Long id){
        ProductVO product = productService.getProductById(id);
        return new ResponseEntity(product,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductVO> updateProduct(@PathVariable Long id,@RequestBody ProductVO productVO){
        ProductVO product = productService.updateProduct(id,productVO);
        log.info("Update Successfully");
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductVO> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        log.info("Delete Successfully");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleException(EntityNotFoundException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex) {
        log.error(ex.getMessage());
        log.error(ex.getClass().getName());
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
