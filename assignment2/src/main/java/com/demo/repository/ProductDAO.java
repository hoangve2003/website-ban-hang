package com.demo.repository;


import com.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
    @Query("select p from  Product p where p.category.id like ?1 and p.name like ?2 and p.price between ?3 and ?4")
    Page<Product> findProductBy(String cid, String keyword, Integer minPrice, Integer maxPrice, Pageable pageable);
}

