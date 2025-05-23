package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.dto.Articles;

import java.util.List;

@Mapper
public interface ArticlesMapper extends BaseMapper<Articles> {
    Integer addArt(Articles articles);

    List<Articles> limitArticles(String text,Integer start, Integer size);

    List<Articles> findArticleAll();

    Articles getArticleById(Integer aid);

    int upDataArticles(Articles articles);

    int  delFart(Integer aid);

    @Select("select count(*) from db_articles")
    Integer getArtCount();

//    @Update("UPDATE db_articles SET view_count = view_count + 1 WHERE aid= #{aid}")
    void incrementViewCount(Integer aid);
}
