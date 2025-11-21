package org.example.service;

import org.example.entity.dto.Articles;
import org.example.entity.vo.request.ArticleRequest;
import org.example.entity.vo.response.ArticlesVO;

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
    
    // 新增方法处理业务逻辑
    Object addOrUpdateArticle(ArticleRequest articleRequest);
    
    List<ArticlesVO> findAllArticlesVO();
    
    Object deleteArticle(Integer aid);
    
    List<ArticlesVO> findArticlesWithLimitVO(String text, Integer page, Integer limit);
    
    Integer getArticleCount();
    
    ArticlesVO getArticleByIdVO(Integer aid);
}
