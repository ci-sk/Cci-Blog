package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.dto.Articles;
import org.example.mapper.ArticlesMapper;
import org.example.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
