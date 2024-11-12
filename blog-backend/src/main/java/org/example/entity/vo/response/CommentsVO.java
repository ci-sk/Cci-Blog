package org.example.entity.vo.response;

import lombok.Data;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CommentsVO{
    private Integer cid;
    private Integer aid;
    private Integer reply_cid;
    private String title;
    private String username;
    private String reply_username;
    private String content;
    private Date time;
    private List<CommentsVO> children; // 新增属性，用于存储子评论列表

    // 新增方法，用于获取某个评论的所有回复
    public static List<CommentsVO> getReplies(Integer cid, List<CommentsVO> comments) {
        List<CommentsVO> replies = new ArrayList<>();
        for (CommentsVO vo : comments) {
            if (vo.getReply_cid()!= null && vo.getReply_cid().equals(cid)) {
                replies.add(vo);
            }
        }
        return replies;
    }

    // 新增方法，用于获取某个评论的所有子评论
    public static List<CommentsVO> getSubComments(Integer cid, List<CommentsVO> comments) {
        List<CommentsVO> subComments = new ArrayList<>();
        List<CommentsVO> replies = getReplies(cid,comments);
        for (CommentsVO reply : replies) {
            subComments.add(reply);
            subComments.addAll(getSubComments(reply.getCid(),comments));
        }
        return subComments;
    }

    // 新增方法，用于设置某个评论的子评论列表
    public static List<CommentsVO> setSubComments(Integer cid, List<CommentsVO> comments, List<CommentsVO> subComments) {
        for (CommentsVO comment : comments) {
            if(comment.getReply_cid() ==null){
                if (comment.getCid().equals(cid)) {
                    comment.setChildren(subComments);
                    break; // 找到后就可以停止循环了
                }
            }
        }
        return comments;
    }

}
