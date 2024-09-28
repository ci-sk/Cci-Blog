package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.dto.Articles;

import java.util.List;

@Mapper
public interface ArticlesMapper extends BaseMapper<Articles> {
    Integer addArt(Articles articles);

    List<Articles> findArticles(Integer start,Integer size);

    List<Articles> findArticleAll();

    int upDataArticles(Integer aid,String title,String content);

    @Select("select count(*) from db_articles")
    public Integer getCount();
}
