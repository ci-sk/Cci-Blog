package org.example.utils;

import org.example.entity.vo.response.BaseCommentVO;
import java.util.ArrayList;
import java.util.List;

public class CommentUtils {
    public static <T extends BaseCommentVO<T>> List<T> getReplies(Integer cid, List<T> comments) {
        List<T> replies = new ArrayList<>();
        for (T vo : comments) {
            if (vo.getReply_cid() != null && vo.getReply_cid().equals(cid)) {
                replies.add(vo);
            }
        }
        return replies;
    }

    public static <T extends BaseCommentVO<T>> List<T> getSubComments(Integer cid, List<T> comments) {
        List<T> subComments = new ArrayList<>();
        List<T> replies = getReplies(cid, comments);
        for (T reply : replies) {
            subComments.add(reply);
            subComments.addAll(getSubComments(reply.getCid(), comments));
        }
        return subComments;
    }

    // 新增方法，用于设置某个评论的子评论列表
    public static <T extends BaseCommentVO<T>> List<T> setSubComments(Integer cid, List<T> comments, List<T> subComments) {
        for (T comment : comments) {
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