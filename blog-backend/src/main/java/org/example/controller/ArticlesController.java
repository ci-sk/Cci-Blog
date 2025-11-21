package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.example.entity.RestBean;
import org.example.entity.dto.Articles;
import org.example.entity.vo.request.ArticleRequest;
import org.example.entity.vo.response.ArticlesVO;
import org.example.service.impl.ArticlesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.util.Date;

/**
 * 文章控制器
 * 该类负责处理与文章相关的HTTP请求，包括添加、查询、删除和分页查询文章等操作。
 */
@Controller
@RequestMapping("/api")
public class ArticlesController {

    @Autowired
    org.example.service.ArticlesService articlesService;

    /**
     * 添加或更新文章
     * @param articleRequest 文章请求对象
     * @return RestBean<?> 对象，包含添加或更新文章的结果
     */
    @ResponseBody
    @PutMapping("/addArticle")
    public RestBean<?> addOrUpdateArticle(@Valid @ModelAttribute ArticleRequest articleRequest)
    {
        try {
            Object result = articlesService.addOrUpdateArticle(articleRequest);
            if (result != null) {
                if (articleRequest.getAid() == null || articleRequest.getAid() <= 0) {
                    return RestBean.db_add_success(result, "添加成功");
                } else {
                    return RestBean.db_update_success(result, "修改成功");
                }
            }
            return RestBean.db_failure();
        } catch (DataIntegrityViolationException e) {
            return RestBean.db_add_failure(500,"添加或更新文章时违反数据完整性约束");
        }
    }

    /**
     * 查询所有文章
     * @return RestBean<?> 对象，包含查询所有文章的结果
     */
    @ResponseBody
    @GetMapping("/getAll/Article")
    public RestBean<?> findAllArticles()
    {
        try{
            List<ArticlesVO> articlesVO = articlesService.findAllArticlesVO();

            if(articlesVO != null){
                return RestBean.success(articlesVO);
            } else {
                return RestBean.db_failure();
            }
        } catch (Exception e) {
            return RestBean.db_un_failure("查询文章时发生错误: " + e.getMessage());
        }
    }

    /**
     * 删除文章
     * @param aid 文章 ID
     * @return RestBean<?> 对象，包含删除文章的结果
     */
    @ResponseBody
    @PutMapping("/delArticle")
    public RestBean<?> deleteArticle(@RequestParam Integer aid)
    {
        Object result = articlesService.deleteArticle(aid);
        if (result instanceof String) {
            return RestBean.db_un_failure((String)result);
        } else if (result instanceof Boolean && (Boolean)result) {
            return RestBean.success();
        } else {
            return RestBean.db_failure();
        }
    }

    /**
     * 分页查询文章
     * @param text 搜索文本
     * @param page 页码
     * @param limit 每页数量
     * @return RestBean<?> 对象，包含分页查询文章的结果
     */
    @ResponseBody
    @RequestMapping("/getLimit/Article")
    public RestBean<?> getArticleLimit(
                                       @RequestParam(required = false) String text,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer limit)
    {
        List<ArticlesVO> articlesVO = articlesService.findArticlesWithLimitVO(text, page, limit);

        if (articlesVO != null) {
            return RestBean.success(articlesVO);
        } else {
            return RestBean.db_failure();
        }
    }

    /**
     * 获取文章数量
     * @return RestBean<?> 对象，包含获取文章数量的结果
     */
    @ResponseBody
    @RequestMapping("/getCount/Article")
    public RestBean<?> getArticleCount()
    {
        Integer count = articlesService.getArticleCount();
        if (count != null && count > 0) {
            return RestBean.success(count);
        } else {
            return RestBean.db_failure();
        }
    }


/**
 * 更新文字阅读量
 * @param aid 文章ID
 * */
    @ResponseBody
    @GetMapping("/getById/article/{aid}")
    public RestBean<?> getArticle(HttpServletResponse response,@PathVariable Integer aid)
    {
        response.setContentType("application/json");
        // 更新阅读量
        articlesService.incrementViewCount(aid);
        // 获取文章详情
        ArticlesVO article = articlesService.getArticleByIdVO(aid);
        return RestBean.success(article);
    }
}
