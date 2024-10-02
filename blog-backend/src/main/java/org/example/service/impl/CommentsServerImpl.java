package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.dto.Comments;
import org.example.mapper.CommentsMapper;
import org.example.service.CommentsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServerImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsServer {

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


}
