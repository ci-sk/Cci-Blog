package org.example.service;

import org.example.entity.dto.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    List<Category> getCategoryLimit(String text, Integer page, Integer size);

    Integer getCountCategory();

    boolean addCategory(String name,String description);

    boolean updateCategory(Integer id, Category category);

    boolean deleteCategory(Integer id);
} 