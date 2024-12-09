package org.example.service;

import org.example.entity.dto.Articles;

import java.util.List;

public interface ArticlesService {
    Integer addArt(Articles articles);

    List<Articles> limitArticles(String text,Integer start,Integer size);

    List<Articles> findArticleAll();

    Articles getArticleById(Integer aid);

    int upDataArticles(Articles articles);

    int  delFart(Integer aid);

    int getArtCount();
    
    void incrementViewCount(Integer articleId);
}
