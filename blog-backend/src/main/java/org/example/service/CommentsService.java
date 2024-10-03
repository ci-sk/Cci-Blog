package org.example.service;

import org.example.entity.dto.Comments;

import java.util.List;

public interface CommentsService {

    int insertComments(Comments comments);

    int deleteComments(Integer cid);

    List<Comments> getCommentsAll();

    List<Comments> getCommentsByAid(Integer aid);
}
