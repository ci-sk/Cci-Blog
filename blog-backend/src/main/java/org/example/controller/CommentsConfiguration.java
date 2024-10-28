package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.RestBean;
import org.example.entity.dto.Comments;
import org.example.entity.vo.response.CommentsVO;
import org.example.service.impl.CommentsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论配置类，处理与评论相关的 HTTP 请求
 */
@Controller
@RequestMapping("/api")
public class CommentsConfiguration  {

    @Autowired
    CommentsServiceImpl server;

    /**
     * 添加评论
     * @param response HttpServletResponse 对象，用于设置响应内容类型和字符编码
     * @param request HttpServletRequest 对象，用于获取请求中的用户 ID
     * @param aid 文章 ID
     * @param content 评论内容
     * @return RestBean<?> 对象，包含添加评论的结果
     */
    @ResponseBody
    @RequestMapping("/addCom")
    public RestBean<?> add(HttpServletResponse response, HttpServletRequest request, Integer aid, String content)
    {
        response.setContentType("application/json;charset=utf-8");

        int uid = (int) request.getAttribute("id");

        System.out.println(uid);

        Comments comments = new Comments();
        comments.setAid(aid);
        comments.setUid(uid);
        comments.setContent(content);
        comments.setC_time(new Date());


        if(server.insertComments(comments) ==1){
            return RestBean.success(comments);
        }
        return RestBean.db_un_failure("评论失败");
    }

    /**
     * 根据文章 ID 获取评论
     * @param response HttpServletResponse 对象，用于设置响应内容类型和字符编码
     * @param aid 文章 ID
     * @return RestBean<?> 对象，包含获取评论的结果
     */
    @ResponseBody
    @RequestMapping("/getAid")
    public RestBean<?> getAid(HttpServletResponse response,int  aid)
    {
        response.setContentType("application/json;charset=utf-8");

        System.out.println(aid);

        List<Comments> commentsList = server.getCommentsByAid(aid);

        if(commentsList!= null){

            ArrayList<CommentsVO> vo = new ArrayList<>();

            for (Comments comments : commentsList) {

                CommentsVO vo1 = (comments.asViewObject(CommentsVO.class, v -> {
                    v.setCid(comments.getCid());
                    v.setAid(comments.getAid());
                    v.setUsername(comments.getAccount().getUsername());
                    v.setContent(comments.getContent());
                    v.setTime(comments.getC_time());
                }));
                vo.add(vo1);
            }
            return RestBean.success(vo);
        }
        else{
            return RestBean.db_un_failure("获取失败");
        }
    }

    /**
     * 获取所有评论
     * @param response HttpServletResponse 对象，用于设置响应内容类型和字符编码
     * @return RestBean<?> 对象，包含获取所有评论的结果
     */
    @ResponseBody
    @RequestMapping("/find/Comments")
    public RestBean<?> getAll(HttpServletResponse response)
    {
        response.setContentType("application/json;charset=utf-8");

        List<Comments> commentsList = server.getCommentsAll();

        System.out.println(commentsList);

        if(commentsList!= null){

            ArrayList<CommentsVO> vo = new ArrayList<>();

            for (Comments comments : commentsList) {
                CommentsVO vo1 = (comments.asViewObject(CommentsVO.class, v -> {
                    v.setCid(comments.getCid());
                    v.setTitle(comments.getArticle().getTitle());
                    v.setUsername(comments.getAccount().getUsername());
                    if(comments.getReplyAccount() != null)
                        v.setReply_username(comments.getReplyAccount().getUsername());
                    v.setContent(comments.getContent());
                    v.setTime(new Date());
                }));
                vo.add(vo1);
            }

            return RestBean.success(vo);
        }
        else{
            return RestBean.db_un_failure("获取失败");
        }
    }

    /**
     * 删除评论
     * @param response HttpServletResponse 对象，用于设置响应内容类型和字符编码
     * @param cid 评论 ID
     * @return RestBean<?> 对象，包含删除评论的结果
     */
    @ResponseBody
    @RequestMapping("/delCom")
    public RestBean<?> delete(HttpServletResponse response,int  cid)
    {
        response.setContentType("application/json;charset=utf-8");

        System.out.println(cid);

        if(server.deleteComments(cid) == 1){
            return RestBean.success("删除成功");
        }
        return RestBean.db_un_failure("删除失败");
    }

}
