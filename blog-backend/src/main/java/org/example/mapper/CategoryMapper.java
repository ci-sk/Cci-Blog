package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.dto.Category;
import org.example.entity.vo.response.CategoryStatsVO;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    List<Category> getAllCategory();

    List<Category> getCategoryLimit(String text,Integer page, Integer size);

    int getCountCategory();

    int addCategory(String name);

    int updateCategory(Category category);

    int deleteCategory(Integer id);

    List<CategoryStatsVO> getCategoryStats();

} 