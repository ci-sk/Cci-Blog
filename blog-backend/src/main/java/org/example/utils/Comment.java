package org.example.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer cid;
    private Integer aid;
    private Integer reply_cid;
    private String title;
    private String username;
    private String reply_username;
    private String content;

    public Comment(Integer cid, Integer aid,Integer reply_cid ,String title,String username,String reply_username,String content,Date time) {
        this.aid = aid;
        this.cid = cid;
        this.content = content;
        this.reply_cid = reply_cid;
        this.reply_username = reply_username;
        this.time = time;
        this.title = title;
        this.username = username;
    }

    private Date time;
    private List<Comment> subComments; // 新增属性，用于存储子评论列表

    // 声明一个静态数组变量
    private static Comment[] comments;

    // 静态初始化块，用于初始化数组
    static {
        comments = new Comment[6];
        comments[0] = new Comment(1, 101, null, "Title 1", "user1", null, "Content 1", new Date());
        comments[1] = new Comment(2, 101, 1, "Title 2", "user2", "user1", "Content 2", new Date());
        comments[2] = new Comment(3, 101, 2, "Title 3", "user3", "user2", "Content 3", new Date());
        comments[3] = new Comment(4,102,null,"Title 4","user4",null,"Content 4",new Date());
        comments[4] = new Comment(5,102,4,"Title 5","user5","user4","Content 5",new Date());
        comments[5] = new Comment(6,102,5,"Title 6","user6","user5","Content 6",new Date());
    }

    // 新增方法，用于获取某个评论的所有回复
    public static List<Comment> getReplies(Integer cid) {
        List<Comment> replies = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getReply_cid()!= null && comment.getReply_cid().equals(cid)) {
                replies.add(comment);
            }
        }
        return replies;
    }

    // 新增方法，用于获取某个评论的所有子评论
    public static List<Comment> getSubComments(Integer cid) {
        List<Comment> subComments = new ArrayList<>();
        List<Comment> replies = getReplies(cid);
        for (Comment reply : replies) {
            subComments.add(reply);
            subComments.addAll(getSubComments(reply.getCid()));
        }
        return subComments;
    }

    // 新增方法，用于查找评论
    private static Comment findComment(Integer cid) {
        for (Comment comment : comments) {
            if (comment.getCid().equals(cid)) {
                return comment;
            }
        }
        return null;
    }

    // 新增方法，用于设置某个评论的子评论列表
    public static void setSubComments(Integer cid, List<Comment> subComments) {
        for (Comment comment : comments) {
            if(comment.getReply_cid() ==null){
                System.out.println("无回复: " + comment.getCid());
                if (comment.getCid().equals(cid)) {
                    System.out.println("添加子评论");
                    comment.setSubComments(subComments);
                    break; // 找到后就可以停止循环了
                } else {
                    System.out.println("未找到对应的评论 ID: " + cid);
                }
            }else {
                System.out.println("有回复: " + comment.getCid());
            }
        }
    }


    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();


        // 遍历数组，输出每个评论的信息
//        for (Comment comment : Comment.comments) {
//            System.out.println("评论 ID: " + comment.getCid());
//            System.out.println("文章 ID: " + comment.getAid());
//            System.out.println("回复 评论 ID: " + comment.getReply_cid());
//            System.out.println("文章标题: " + comment.getTitle());
//            System.out.println("评论用户: " + comment.getUsername());
//            System.out.println("回复用户: " + comment.getReply_username());
//            System.out.println("内容: " + comment.getContent());
//            System.out.println("时间: " + comment.getTime());
//            System.out.println("-------------------------");
//        }

        // 获取评论1的所有子评论
//        List<Comment> subCommentsToComment1 = getSubComments(1);
//        System.out.println("Subcomments to Comment 1:");
//        for (Comment subComment : subCommentsToComment1) {
//            System.out.println("评论 ID: " + subComment.getCid());
//            System.out.println("文章 ID: " + subComment.getAid());
//            System.out.println("回复 评论 ID: " + subComment.getReply_cid());
//            System.out.println("文章标题: " + subComment.getTitle());
//            System.out.println("评论用户: " + subComment.getUsername());
//            System.out.println("回复用户: " + subComment.getReply_username());
//            System.out.println("内容: " + subComment.getContent());
//            System.out.println("时间: " + subComment.getTime());
//            System.out.println("-------------------------");
//        }

        // 设置评论1的子评论列表
        for (Comment comment : Comment.comments) {
            int cid = comment.getCid();

            setSubComments(cid, getSubComments(cid));

        }

        // 输出评论1的子评论列表
        System.out.println("Subcomments to Comment 1:");
        for (Comment subComment : Comment.comments) {
            System.out.println("评论 ID: " + subComment.getCid());
            System.out.println("文章 ID: " + subComment.getAid());
            System.out.println("回复 评论 ID: " + subComment.getReply_cid());
            System.out.println("文章标题: " + subComment.getTitle());
            System.out.println("评论用户: " + subComment.getUsername());
            System.out.println("回复用户: " + subComment.getReply_username());
            System.out.println("内容: " + subComment.getContent());
            System.out.println("时间: " + subComment.getTime());
            System.out.println("子评论: " + subComment.getSubComments());
            System.out.println("-------------------------");

        }

        // 在这里放置你想要测量运行时间的代码
        // 记录程序结束时间
        long endTime = System.currentTimeMillis();

        // 计算运行时间（以毫秒为单位）
        long runTime = endTime - startTime;

        // 输出运行时间
        System.out.println("程序运行时间：" + runTime + " 毫秒");
    }



}
