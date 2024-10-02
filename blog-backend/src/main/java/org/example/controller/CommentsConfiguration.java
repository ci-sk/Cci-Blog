package org.example.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.RestBean;
import org.example.entity.dto.Account;
import org.example.entity.dto.Comments;
import org.example.service.CommentsServer;
import org.example.service.impl.CommentsServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/auth/com")
public class CommentsConfiguration  {

    @Autowired
    CommentsServerImpl server;


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

        List<Comments> commentsList = server.getCommentsByAid(23);

        System.out.println(commentsList+"!!");

        if(commentsList!= null){
            return RestBean.success(commentsList);
        }
        else{
            return RestBean.db_un_failure("获取失败");
        }

    }

}
