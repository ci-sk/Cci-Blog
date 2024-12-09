package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.dto.Category;
import org.example.entity.vo.response.CategoryStatsVO;
import org.example.mapper.CategoryMapper;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    CategoryMapper mapper;

    @Override
    public List<Category> getAllCategories() {
        return mapper.getAllCategory();
    }

    @Override
    public List<Category> getCategoryLimit(String text, Integer page, Integer size) {
        return mapper.getCategoryLimit(text,page, size);
    }

    @Override
    public Integer getCountCategory() {
        return mapper.getCountCategory();
    }

    @Override
    public boolean addCategory(String name) {
        return mapper.addCategory(name) > 0;
    }

    @Override
    public boolean updateCategory(Integer id, Category category) {
        category.setId(id);
        return mapper.updateCategory(category) > 0;
    }

    @Override
    public boolean deleteCategory(Integer id) {
        return mapper.deleteCategory(id) > 0;
    }

    @Override
    public List<CategoryStatsVO> getCategoryStats() {
        return mapper.getCategoryStats();
    }

} 