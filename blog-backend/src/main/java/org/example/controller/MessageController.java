package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.RestBean;
import org.example.entity.dto.Message;
import org.example.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 留言控制器
 */
@Controller
@RequestMapping("/api")
public class MessageController {

    @Autowired
    MessageServiceImpl service;

    /**
     * 留言消息
     * @param response 响应
     * @param content 内容
     * @return 响应结果
     */
    @ResponseBody
    @RequestMapping("/addMessage")
    public RestBean<?> add(HttpServletResponse response,String username, String content){
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
     * 查询留言
     * @param response 响应
     * @return 响应结果
     */
    @ResponseBody
    @RequestMapping("/getLimit/Message")
    public RestBean<?> find(HttpServletResponse response,String content,Integer page,Integer limit){
        response.setContentType("application/json;charset=utf-8");

        page--;
        if (page >= 1) {
            page = (page) * 10;
            limit += page;
        }

        List<Message> message = service.getMessage(content, page, limit);

        return RestBean.db_success(message,"请求成功");
    }

    /**
     * 删除留言
     * @param response 响应
     * @param mid 消息id
     * @return 响应结果
     */
    @ResponseBody
    @RequestMapping("/delMessage")
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
    @RequestMapping("/getCount/Message")
    public RestBean<?> getMsgCount(HttpServletResponse response){
        response.setContentType("application/json;charset=utf-8");

        return RestBean.success(service.getMessageCount());
    }


     @GetMapping("/message/unread/count")
    @ResponseBody
    public RestBean<Integer> getUnreadCount() {
        return RestBean.success(service.countUnreadMessages());
    }

    @GetMapping("/message/recent")
    @ResponseBody
    public RestBean<List<Message>> getRecentMessages() {
        List<Message> recentMessages = service.getRecentMessages(10);

        return RestBean.success(recentMessages); // 获取最近10条
    }

    @PostMapping("/message/read/all")
    @ResponseBody
    public RestBean<Void> markAllAsRead() {
        service.markAllAsRead();
        return RestBean.success();
    }
}
