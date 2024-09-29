package org.example.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.RestBean;
import org.example.entity.dto.Articles;
import org.example.entity.vo.response.ArticlesVO;
import org.example.service.impl.ArticlesServerImpl;
import org.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/admin/api/art")
public class ArticlesController {
    @Autowired
    ArticlesServerImpl artServer;

    @Resource
    JwtUtils utils;

    @ResponseBody
    @PutMapping("/addArt")
    public RestBean<?> addArt(Integer aid, String title, String content) {
        try {
            if (aid == null || aid <= 0) {
                Articles articles = new Articles(null, title, content, new Date());
                System.out.println(articles);
                if (artServer.addArt(articles) == 1) return RestBean.db_add_success(articles, "添加成功");
            } else {
                System.out.println("修改接口");
                // 判断id是否存在
                if (artServer.upDataArticles(aid, title, content) == 1) {
                    return RestBean.db_update_success(new Articles(aid, title, content), "修改成功");
                }
            }
            return RestBean.db_failure();
        } catch (DataIntegrityViolationException e) {
//            log.error("添加或更新文章时违反数据完整性约束", e);
            return RestBean.db_add_failure(500,"添加或更新文章时违反数据完整性约束");
        }
    }


    @ResponseBody
    @GetMapping("/find")
    public RestBean<?> findArticles(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try{
        List<Articles> articles = artServer.findArticleAll();

        if(articles!= null){

           return RestBean.success(artServer.findArticleAll());
        } else {
            response.getWriter().write(RestBean.db_failure().asJsonString());
        }
    } catch (IOException e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return (RestBean.db_un_failure("Internal server error"));
    }
        return RestBean.db_failure();
    }

    @ResponseBody
    @PutMapping("/delF")
    public RestBean<?> delArt(HttpServletResponse response, Integer aid, Integer del) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (artServer.delFart(aid, del) == 1) return (RestBean.success());
        else return(RestBean.db_failure());
    }


}
