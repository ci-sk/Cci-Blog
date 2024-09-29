package org.example.service;

import org.example.entity.dto.Articles;

import java.util.List;

public interface ArticlesServer {
    Integer addArt(Articles articles);

    List<Articles> limitArticles(Integer start,Integer size);

    List<Articles> findArticleAll();

    int upDataArticles(Integer aid,String title,String content);

    int  delFart(Integer aid,Integer del);

}
