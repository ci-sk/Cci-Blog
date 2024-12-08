package org.example.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.dto.Category;
import org.example.entity.RestBean;
import org.example.entity.dto.Tag;
import org.example.entity.vo.response.CategoryStatsVO;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @ResponseBody
    @GetMapping("/getAll/Category")
    public RestBean<?> getAllCategories(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Category> categories = categoryService.getAllCategories();

        return RestBean.success(categories);
    }

    @ResponseBody
    @PutMapping("/addCategory")
    public RestBean<?> addCategory(HttpServletResponse response, String name) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        boolean success = categoryService.addCategory(name);
        if (success) {
            return RestBean.success(new Category().setName(name));
        } else {
            return RestBean.failure(400, "分类添加失败");
        }
    }

    @ResponseBody
    @PutMapping("/updateCategory")
    public RestBean<?> updateCategory(HttpServletResponse response,Integer id,Category category) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        boolean success = categoryService.updateCategory(id, category);
        if (success) {
            return RestBean.success("分类更新成功");
        } else {
            return RestBean.failure(400, "分类更新失败");
        }
    }

    @ResponseBody
    @DeleteMapping("/delCategory")
    public RestBean<?> deleteCategory(HttpServletResponse response,Integer id) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        boolean success = categoryService.deleteCategory(id);
        if (success) {
            return RestBean.success("分类删除成功");
        } else {
            return RestBean.failure(400, "分类删除失败");
        }
    }

    @ResponseBody
    @PutMapping("/getLimit/Category")
    public RestBean<?> getLimitCategories(HttpServletResponse response, String text, Integer page, Integer size) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        System.out.println(text+" "+page + " " + size);

        page--;
        if (page >= 1) {
            page = (page) * 10;
            size += page;
        }

        List<Category> categories = categoryService.getCategoryLimit(text,page, size);
        return RestBean.success(categories);
    }

    @ResponseBody
    @GetMapping("/getCount/Category")
    public RestBean<?> getCountCategories(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Integer count = categoryService.getCountCategory();
        return RestBean.success(count);
    }

    @ResponseBody
    @GetMapping("/category/stats")
    public RestBean<?> getCategoryStats(HttpServletResponse response,Integer id) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        List<CategoryStatsVO> categoryStats = categoryService.getCategoryStats();


        return RestBean.success(categoryStats);
    }
} 