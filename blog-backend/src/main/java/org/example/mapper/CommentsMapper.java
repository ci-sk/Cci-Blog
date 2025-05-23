package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.entity.dto.Comments;

import java.util.List;

@Mapper
public interface CommentsMapper extends BaseMapper<Comments> {

    int insertComments(Comments comments);

    int deleteComments(Integer cid);

    List<Comments> getCommentsAll();

    List<Comments> getCommentsByAid(Integer aid);

    List<Comments> getCommentsLimit(@Param("text") String text,
                                    @Param("page") Integer page,
                                    @Param("limit") Integer limit);

    int getCommentsCount();

    //计算未读评论数量
    @Select("SELECT COUNT(*) FROM db_comments WHERE is_read = #{isRead}")
    int countByIsRead(int isRead);

    //查询最近的评论
    @Select("SELECT * FROM db_comments ORDER BY time DESC LIMIT #{limit}")
    List<Comments> findRecentComments(int limit);

    //更新所有评论为已读
    @Update("UPDATE db_comments SET is_read = 1 WHERE is_read = 0")
    void updateAllToRead();
}
