package org.example.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.RestBean;
import org.example.entity.dto.Articles;
import org.example.service.impl.ArticlesServerImpl;
import org.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public void addArt(HttpServletResponse response, HttpServletRequest request, Integer aid, String title, String content) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            if (aid == null || aid <= 0) {
                Articles articles = new Articles(null, title, content, new Date());
                System.out.println(articles);
                if (artServer.addArt(articles) == 1)
                    response.getWriter().write(RestBean.db_success(articles, "添加成功").asJsonString());
            } else {
                System.out.println("修改接口");
                //判断id是否存在
                if (artServer.upDataArticles(aid, title, content) == 1) {
                    response.getWriter().write(RestBean.db_success(new Articles(aid, title, content), "修改成功").asJsonString());
                }
            }
        } catch (DataIntegrityViolationException e) {
            response.getWriter().write(RestBean.db_failure().asJsonString());
        }
    }

    @ResponseBody
    @GetMapping("/find")
    public void findArticles(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(RestBean.success(artServer.findArticleAll()).asJsonString());
    }



}
