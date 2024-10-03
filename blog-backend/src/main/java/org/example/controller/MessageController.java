package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.RestBean;
import org.example.entity.dto.Message;
import org.example.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/api")
public class MessageController {

    @Autowired
    MessageServiceImpl service;


    @ResponseBody
    @RequestMapping("/addMsg")
    public RestBean<?> add(HttpServletResponse response, HttpServletRequest request, String content){
        response.setContentType("application/json;charset=utf-8");

        Message message = new Message();
        message.setUid((int) request.getAttribute("id"));
        message.setContent(content);
        message.setM_time(new Date());

        if(service.addMessage(message) ==1){
            return RestBean.success(message);
        }else{
            return RestBean.db_un_failure("添加失败");
        }
    }

    @ResponseBody
    @RequestMapping("/find/msg")
    public RestBean<?> find(HttpServletResponse response, HttpServletRequest request){
        response.setContentType("application/json;charset=utf-8");

        return RestBean.success(service.getMessage());
    }

    @ResponseBody
    @RequestMapping("/delMsg")
    public RestBean<?> delete(HttpServletResponse response,Integer mid){
        response.setContentType("application/json;charset=utf-8");

        if(service.deleteMessage(mid) ==1){
            return RestBean.success("删除成功");
        }else{
            return RestBean.db_un_failure("删除失败");
        }
    }

}
