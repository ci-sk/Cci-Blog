package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entity.dto.Comments;

import java.util.List;

@Mapper
public interface CommentsMapper extends BaseMapper<Comments> {

    int insertComments(Comments comments);

    int deleteComments(Integer cid);

    List<Comments> getCommentsAll();


    List<Comments> getCommentsByAid(Integer aid);


}
