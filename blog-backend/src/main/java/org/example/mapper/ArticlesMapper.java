package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.dto.Articles;

import java.util.List;

@Mapper
public interface ArticlesMapper extends BaseMapper<Articles> {
    Integer addArt(Articles articles);

    List<Articles> limitArticles(Integer start,Integer size);

    List<Articles> findArticleAll();

    int upDataArticles(Articles articles);

    int  delFart(Integer aid,Integer del);

    @Select("select count(*) from db_articles")
    public Integer getCount();
}
