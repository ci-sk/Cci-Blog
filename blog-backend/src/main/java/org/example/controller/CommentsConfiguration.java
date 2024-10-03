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

@Controller
@RequestMapping("/api/auth/com")
public class CommentsConfiguration  {

    @Autowired
    CommentsServiceImpl server;


    @ResponseBody
    @RequestMapping("/add")
    public RestBean<?> add(HttpServletResponse response, HttpServletRequest request, Integer aid, String content){
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

    @ResponseBody
    @RequestMapping("/getAid")
    public RestBean<?> getAid(HttpServletResponse response,int  aid){
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
                    v.setC_time(new Date());
                }));
                vo.add(vo1);
            }
            return RestBean.success(vo);
        }
        else{
            return RestBean.db_un_failure("获取失败");
        }
    }

    @ResponseBody
    @RequestMapping("/getAll")
    public RestBean<?> getAll(HttpServletResponse response){
        response.setContentType("application/json;charset=utf-8");

        List<Comments> commentsList = server.getCommentsAll();

        if(commentsList!= null){

            ArrayList<CommentsVO> vo = new ArrayList<>();

            for (Comments comments : commentsList) {
                CommentsVO vo1 = (comments.asViewObject(CommentsVO.class, v -> {
                    v.setCid(comments.getCid());
                    v.setTitle(comments.getArticle().getTitle());
                    v.setUsername(comments.getAccount().getUsername());
                    v.setContent(comments.getContent());
                    v.setC_time(new Date());
                }));
                vo.add(vo1);
            }

            return RestBean.success(vo);
        }
        else{
            return RestBean.db_un_failure("获取失败");
        }
    }

    @ResponseBody
    @RequestMapping("/admin/delete")
    public RestBean<?> delete(HttpServletResponse response,int  cid){
        response.setContentType("application/json;charset=utf-8");

        System.out.println(cid);

        if(server.deleteComments(cid) == 1){
            return RestBean.success("删除成功");
        }
        return RestBean.db_un_failure("删除失败");
    }

}
