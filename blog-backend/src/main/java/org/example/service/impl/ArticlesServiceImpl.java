package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.dto.Articles;
import org.example.entity.vo.request.ArticleRequest;
import org.example.entity.vo.response.ArticlesVO;
import org.example.mapper.ArticlesMapper;
import org.example.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticlesServiceImpl extends ServiceImpl<ArticlesMapper, Articles> implements ArticlesService {

    @Autowired
    ArticlesMapper mapper;


    @Override
    public Integer addArt(Articles articles) {
        return mapper.addArt(articles);
    }

    @Override
    public List<Articles> limitArticles(String text,Integer start, Integer size) {
        return mapper.limitArticles(text,start,size);
    }

    @Override
    public List<Articles> findArticleAll() {
        return mapper.findArticleAll();
    }

    @Override
    public Articles getArticleById(Integer aid) {
        return mapper.getArticleById(aid);
    }

    @Override
    public int upDataArticles(Articles articles){
        return mapper.upDataArticles(articles);
    }

    @Override
    public int delFart(Integer aid) {
        return mapper.delFart(aid);
    }

    @Override
    public int getArtCount() {
        return mapper.getArtCount();
    }

    @Override
    public void incrementViewCount(Integer articleId) {
        mapper.incrementViewCount(articleId);
    }
    
    @Override
    public Object addOrUpdateArticle(ArticleRequest articleRequest) {
        try {
            Articles articles = new Articles();
            if (articleRequest.getAid() == null || articleRequest.getAid() <= 0) {
                articles.setTitle(articleRequest.getTitle())
                        .setContent(articleRequest.getContent())
                        .setTags(articleRequest.getTags())
                        .setDesc(articleRequest.getDesc())
                        .setImg_url(articleRequest.getImg_url())
                        .setDel(articleRequest.getDel())
                        .setCategoryId(articleRequest.getCategoryId())
                        .setPublish_Time(new Date());
                if (mapper.addArt(articles) == 1) return articles;
            } else {
                articles.setAid(articleRequest.getAid())
                        .setTitle(articleRequest.getTitle())
                        .setContent(articleRequest.getContent())
                        .setTags(articleRequest.getTags())
                        .setDesc(articleRequest.getDesc())
                        .setDel(articleRequest.getDel())
                        .setCategoryId(articleRequest.getCategoryId())
                        .setImg_url(articleRequest.getImg_url());
                // 判断id是否存在
                if (mapper.upDataArticles(articles) == 1) {
                    return articles;
                }
            }
            return null;
        } catch (DataIntegrityViolationException e) {
            throw e;
        }
    }
    
    @Override
    public List<ArticlesVO> findAllArticlesVO() {
        List<Articles> articles = mapper.findArticleAll();
        if(articles != null){
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
            return vo;
        }
        return null;
    }
    
    @Override
    public Object deleteArticle(Integer aid) {
        if (aid == null) {
            return "aid参数不能为空";
        }
        int result = mapper.delFart(aid);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public List<ArticlesVO> findArticlesWithLimitVO(String text, Integer page, Integer limit) {
        // 防止负数页码
        if (page < 1) page = 1;
        if (limit < 1) limit = 10;
        
        // 计算偏移量
        Integer offset = (page - 1) * limit;
        
        List<Articles> articles = mapper.limitArticles(text, offset, limit);

        if (articles != null) {
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
            return vo;
        }
        return null;
    }
    
    @Override
    public Integer getArticleCount() {
        return mapper.getArtCount();
    }
    
    @Override
    public ArticlesVO getArticleByIdVO(Integer aid) {
        Articles article = mapper.getArticleById(aid);
        if (article != null) {
            return article.asViewObject(ArticlesVO.class, v -> {
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
            });
        }
        return null;
    }

}
