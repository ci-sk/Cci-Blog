package org.example.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.dto.Category;
import org.example.entity.RestBean;
import org.example.entity.vo.response.CategoryStatsVO;
import org.example.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类控制器，处理与分类相关的请求
 */
@Controller
@RequestMapping("/api")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 获取所有分类
     *
     * @param response HTTP 响应对象
     * @return 包含所有分类的响应对象
     */
    @ResponseBody
    @GetMapping("/getAll/Category")
    public RestBean<?> getAllCategories(HttpServletResponse response)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        return RestBean.success(categoryService.getAllCategories());
    }

    /**
     * 添加分类
     *
     * @param response HTTP 响应对象
     * @param name     分类名称
     * @return 添加分类的结果
     */
    @ResponseBody
    @PutMapping("/addCategory")
    public RestBean<?> addCategory(HttpServletResponse response,@RequestParam String name)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        boolean success = categoryService.addCategory(name);
        if (success) {
            return RestBean.success(new Category().setName(name));
        } else {
            return RestBean.failure(400, "分类添加失败");
        }
    }

    /**
     * 更新分类
     *
     * @param response HTTP 响应对象
     * @param id       分类 ID
     * @param category 分类对象
     * @return 更新分类的结果
     */
    @ResponseBody
    @PutMapping("/updateCategory")
    public RestBean<?> updateCategory(HttpServletResponse response,
                                      @RequestParam Integer id,
                                      @ModelAttribute Category category)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        boolean success = categoryService.updateCategory(id, category);
        if (success) {
            return RestBean.success("分类更新成功");
        } else {
            return RestBean.failure(400, "分类更新失败");
        }
    }

    /**
     * 删除分类
     *
     * @param response HTTP 响应对象
     * @param id       分类 ID
     * @return 删除分类的结果
     */
    @ResponseBody
    @DeleteMapping("/delCategory")
    public RestBean<?> deleteCategory(HttpServletResponse response,
                                      @RequestParam Integer id)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        boolean success = categoryService.deleteCategory(id);
        if (success) {
            return RestBean.success("分类删除成功");
        } else {
            return RestBean.failure(400, "分类删除失败");
        }
    }

    /**
     * 获取分页分类
     *
     * @param response HTTP 响应对象
     * @param text     分类名称关键字
     * @param page     页码
     * @param limit     每页大小
     * @return 分页分类的结果
     */
    @ResponseBody
    @PutMapping("/getLimit/Category")
    public RestBean<?> getLimitCategories(HttpServletResponse response,
                                          @RequestParam(required = false) String text,
                                          @RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer limit)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Category> categories = categoryService.getCategoryLimit(text,page,limit);
        return RestBean.success(categories);
    }

    /**
     * 获取分类数量
     *
     * @param response HTTP 响应对象
     * @return 分类数量
     */
    @ResponseBody
    @GetMapping("/getCount/Category")
    public RestBean<?> getCountCategories(HttpServletResponse response)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Integer count = categoryService.getCountCategory();
        return RestBean.success(count);
    }

    /**
     * 获取分类统计信息
     *
     * @param response HTTP 响应对象
     * @return 分类统计信息
     */
    @ResponseBody
    @GetMapping("/category/stats")
    public RestBean<?> getCategoryStats(HttpServletResponse response)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<CategoryStatsVO> categoryStats = categoryService.getCategoryStats();


        return RestBean.success(categoryStats);
    }
}
