package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.dto.Comments;
import org.example.mapper.CommentsMapper;
import org.example.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {

    @Autowired
    CommentsMapper mapper;


    @Override
    public int insertComments(Comments comments) {
        return mapper.insertComments(comments);
    }

    @Override
    public int deleteComments(Integer cid) {
        return mapper.deleteComments(cid);
    }

    @Override
    public List<Comments> getCommentsAll() {
        return mapper.getCommentsAll();
    }

    @Override
    public List<Comments> getCommentsByAid(Integer aid){
        return mapper.getCommentsByAid(aid);
    }

    @Override
    public List<Comments> getCommentsLimit(String content, Integer page, Integer limit) {
        return mapper.getCommentsLimit(content,page,limit);
    }

    @Override
    public int getCommentsCount() {
        return mapper.getCommentsCount();
    }


}
