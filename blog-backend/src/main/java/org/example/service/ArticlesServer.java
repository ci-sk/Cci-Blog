package org.example.service;

import org.example.entity.dto.Articles;

import java.util.List;

public interface ArticlesServer {
    Integer addArt(Articles articles);

    List<Articles> limitArticles(Integer start,Integer size);

    List<Articles> findArticleAll();

    int upDataArticles(Articles articles);

    int  delFart(Integer aid,Integer del);

}
