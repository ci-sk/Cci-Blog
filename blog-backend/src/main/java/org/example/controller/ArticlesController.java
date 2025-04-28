package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    ArticlesServiceImpl artServer;

    /**
     * 添加或更新文章
     * @param reqArt 文章请求对象
     * @return RestBean<?> 对象，包含添加或更新文章的结果
     */
    @ResponseBody
    @PutMapping("/addArticle")
    public RestBean<?> addArt(@ModelAttribute ArticleRequest reqArt)
    {
        try {
            Articles articles = new Articles();
            if (reqArt.getAid() == null || reqArt.getAid() <= 0) {
                articles.setTitle(reqArt.getTitle())
                        .setContent(reqArt.getContent())
                        .setTags(reqArt.getTags())
                        .setDesc(reqArt.getDesc())
                        .setImg_url(reqArt.getImg_url())
                        .setDel(reqArt.getDel())
                        .setCategoryId(reqArt.getCategoryId())
                        .setPublish_Time(new Date());
                if (artServer.addArt(articles) == 1) return RestBean.db_add_success(articles, "添加成功");
            } else {
                articles.setAid(reqArt.getAid())
                        .setTitle(reqArt.getTitle())
                        .setContent(reqArt.getContent())
                        .setTags(reqArt.getTags())
                        .setDesc(reqArt.getDesc())
                        .setDel(reqArt.getDel())
                        .setCategoryId(reqArt.getCategoryId())
                        .setImg_url(reqArt.getImg_url());
                // 判断id是否存在
                if (artServer.upDataArticles(articles) == 1) {
                    return RestBean.db_update_success(articles, "修改成功");
                }
            }
            return RestBean.db_failure();
        } catch (DataIntegrityViolationException e) {
            return RestBean.db_add_failure(500,"添加或更新文章时违反数据完整性约束");
        }
    }

    /**
     * 查询所有文章
     * @param response HttpServletResponse 对象，用于设置响应内容类型和字符编码
     * @return RestBean<?> 对象，包含查询所有文章的结果
     */
    @ResponseBody
    @GetMapping("/getAll/Article")
    public RestBean<?> findArticles(HttpServletResponse response)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try{
            List<Articles> articles = artServer.findArticleAll();

            if(articles!= null){
                ArrayList<ArticlesVO> vo = new ArrayList<>();
                for (Articles article : articles) {
                    ArticlesVO vo1 = (article.asViewObject(ArticlesVO.class, v -> {
                        v.setAid(article.getAid())
                                .setTitle(article.getTitle())
                                .setContent(article.getContent())
                                .setDesc(article.getDesc())
                                .setTags(article.getTags())
                                .setImg_url(article.getImg_url())
                                .setTime(article.getPublish_Time())
                                .setCategoryId(article.getCategoryId())
                                .setCategory(article.getCategory())
                                .setDel(article.getDel())
                                .setViewCount(article.getViewCount());
                    }));
                    vo.add(vo1);
                }
                return RestBean.success(vo);
            } else {
                response.getWriter().write(RestBean.db_failure().asJsonString());
            }
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return (RestBean.db_un_failure("Internal server error"));
        }
        return RestBean.db_failure();
    }

    /**
     * 删除文章
     * @param response HttpServletResponse 对象，用于设置响应内容类型和字符编码
     * @param aid 文章 ID
     * @return RestBean<?> 对象，包含删除文章的结果
     */
    @ResponseBody
    @PutMapping("/delArticle")
    public RestBean<?> delArt(HttpServletResponse response, @RequestParam Integer aid)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (aid == null) {
            return RestBean.db_un_failure("aid参数不能为空");
        }
        int result = artServer.delFart(aid);
        if (result == 1) {
            return RestBean.success();
        } else {
            return RestBean.db_failure();
        }
    }

    /**
     * 分页查询文章
     * @param response HttpServletResponse 对象，用于设置响应内容类型和字符编码
     * @param text 搜索文本
     * @param page 页码
     * @param limit 每页数量
     * @return RestBean<?> 对象，包含分页查询文章的结果
     */
    @ResponseBody
    @RequestMapping("/getLimit/Article")
    public RestBean<?> getArticleLimit(HttpServletResponse response,
                                       @RequestParam(required = false) String text,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer limit)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        List<Articles> articles = artServer.limitArticles(text,page, limit);

        if (articles!= null) {
            ArrayList<ArticlesVO> vo = new ArrayList<>();
            for (Articles article : articles) {
                ArticlesVO vo1 = (article.asViewObject(ArticlesVO.class, v -> {
                    v.setAid(article.getAid())
                            .setTitle(article.getTitle())
                            .setContent(article.getContent())
                            .setDesc(article.getDesc())
                            .setTags(article.getTags())
                            .setImg_url(article.getImg_url())
                            .setTime(article.getPublish_Time())
                            .setCategoryId(article.getCategoryId())
                            .setCategory(article.getCategory())
                            .setViewCount(article.getViewCount())
                            .setDel(article.getDel());
                }));
                vo.add(vo1);
            }
            return RestBean.success(vo);
        }else {
            return RestBean.db_failure();
        }
    }

    /**
     * 获取文章数量
     * @param response HttpServletResponse 对象，用于设置响应内容类型和字符编码
     * @return RestBean<?> 对象，包含获取文章数量的结果
     */
    @ResponseBody
    @RequestMapping("/getCount/Article")
    public RestBean<?> getArticleCount(HttpServletResponse response)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int count = artServer.getArtCount();
        if (count > 0) {
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
        artServer.incrementViewCount(aid);
        // 获取文章详情
        Articles article = artServer.getArticleById(aid);
        return RestBean.success(article);
    }
}
