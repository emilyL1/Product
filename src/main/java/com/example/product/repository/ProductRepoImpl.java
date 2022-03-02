package com.example.product.repository;

import com.example.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ProductRepoImpl implements ProductRepo {
    @Autowired
    EntityManager em;

    @Override
    public Product save(Product product) {
        em.persist(product);
        return product;
    }

    @Override
    public List<Product> getProducts() {
        Query q = em.createQuery("select p from Product p",Product.class);
        List<Product> products = q.getResultList();
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        Product p = em.find(Product.class,id);
        return p;
    }

    @Override
    public Product updateProduct(Product product) {
        Product p = em.merge(product);
        return p;

    }

    @Override
    public void deleteProduct(Product p) {
        em.remove(p);
    }
}
