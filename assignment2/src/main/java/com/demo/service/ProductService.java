package com.demo.service;

import com.demo.model.Product;
import com.demo.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO: Connect to database
@Service
public class ProductService {
    @Autowired
    ProductDAO productDAO;

    public List<Product> getAllProduct() {
        return productDAO.findAll();
    }
    public Product savePrd(Product product){
        return productDAO.save(product);
    }

    public Page<Product> getAll(Pageable pageable) {
        return productDAO.findAll(pageable);
    }

    public Product findById(int id) {
        return productDAO.findById(id).orElse(null);
    }

    public Page<Product> searchProduct(String cid, String keyword, Integer minPrice, Integer maxPrice, Pageable pageable) {
        return productDAO.findProductBy(cid, keyword, minPrice, maxPrice, pageable);
    }
}
