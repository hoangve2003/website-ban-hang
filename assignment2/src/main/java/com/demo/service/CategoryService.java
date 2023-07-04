package com.demo.service;

import com.demo.model.Category;
import com.demo.repository.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//TODO: Connect to database

@Service
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;
    public List<Category> getAll(){
        return categoryDAO.findAll();
    }
    public Optional<Category> getById(String id){
        return categoryDAO.findById(id);
    }
    public Category addCategory(Category category){
       return categoryDAO.save(category);
    }
}
