package com.example.demo.service.admin;

import java.util.List;

import com.example.demo.entity.Category;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id);
    void save(Category category);
    void deleteById(Long id);
}
