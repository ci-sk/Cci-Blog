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

/**
 * 消息控制器
 */
@Controller
@RequestMapping("/api")
public class MessageController {

    @Autowired
    MessageServiceImpl service;

    /**
     * 添加消息
     * @param response 响应
     * @param request 请求
     * @param content 内容
     * @return 响应结果
     */
    @ResponseBody
    @RequestMapping("/addMsg")
    public RestBean<?> add(HttpServletResponse response, HttpServletRequest request,String username, String content){
        response.setContentType("application/json;charset=utf-8");

        Message message = new Message();
        message.setUsername(username);
        message.setContent(content);
        message.setTime(new Date());

        if(service.addMessage(message) ==1){
            return RestBean.success(message);
        }else{
            return RestBean.db_un_failure("添加失败");
        }
    }

    /**
     * 查询消息
     * @param response 响应
     * @param request 请求
     * @return 响应结果
     */
    @ResponseBody
    @RequestMapping("/getLimit/Message")
    public RestBean<?> find(HttpServletResponse response, HttpServletRequest request,String content,Integer page,Integer limit){
        response.setContentType("application/json;charset=utf-8");

        return RestBean.success(service.getMessage(content,page,limit));
    }

    /**
     * 删除消息
     * @param response 响应
     * @param mid 消息id
     * @return 响应结果
     */
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


    /**
     * 获取数量
     * @param response 响应
     * @return 响应结果
     */
    @ResponseBody
    @RequestMapping("/getMsgCount")
    public RestBean<?> getMsgCount(HttpServletResponse response){
        response.setContentType("application/json;charset=utf-8");

        return RestBean.success(service.getMessageCount());
    }

}
