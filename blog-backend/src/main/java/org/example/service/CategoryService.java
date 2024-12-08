package org.example.service;

import org.example.entity.dto.Category;
import org.example.entity.vo.response.CategoryStatsVO;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    List<Category> getCategoryLimit(String text, Integer page, Integer size);

    Integer getCountCategory();

    boolean addCategory(String name);

    boolean updateCategory(Integer id, Category category);

    boolean deleteCategory(Integer id);

    List<CategoryStatsVO> getCategoryStats();
}