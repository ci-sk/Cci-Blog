package org.example.service;

import org.example.entity.dto.Comments;
import org.example.entity.vo.response.CommVOs;
import org.example.entity.vo.response.CommentsVO;

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
    
    // 新增方法处理业务逻辑
    List<CommVOs> getCommentsByAidVO(Integer aid);
    
    List<CommentsVO> getCommentsAllVO();
    
    List<CommentsVO> getCommentsLimitVO(String content, Integer page, Integer limit);
}
