package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entity.dto.Category;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    List<Category> getAllCategory();

    List<Category> getCategoryLimit(String text,Integer page, Integer size);

    int getCountCategory();

    int addCategory(String name,String description);

    int updateCategory(Category category);

    int deleteCategory(Integer id);
} 