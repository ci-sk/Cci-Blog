package org.example.service;

import org.example.entity.dto.Comments;

import java.util.List;

public interface CommentsService {

    int insertComments(Comments comments);

    int deleteComments(Integer cid);

    List<Comments> getCommentsAll();

    List<Comments> getCommentsByAid(Integer aid);

    List<Comments> getCommentsLimit(String content, Integer page, Integer limit);

    int getCommentsCount();

    int countUnreadMessages();

    List<Comments> getRecentMessages(int limit);

    void markAllAsRead();
}
