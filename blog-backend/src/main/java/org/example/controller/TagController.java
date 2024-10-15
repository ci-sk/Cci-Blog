package org.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.RestBean;
import org.example.entity.dto.Tag;
import org.example.service.impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api")
public class TagController {
    @Autowired
    TagServiceImpl server;

    @ResponseBody
    @RequestMapping("/addTag")
    public RestBean<?> add(HttpServletResponse response,String tagName){
        response.setContentType("application/json;charset=utf-8");

        if(tagName.equals("")){
            return RestBean.db_un_failure("标签名不能为空");
        }

        Tag tag = new Tag();

        tag.setTagName(tagName);
        tag.setTime(new Date());
        if(server.addTag(tag) ==1 ){
            return RestBean.success(tag);
        }
        else return RestBean.db_un_failure("添加失败");
    }

    @ResponseBody
    @RequestMapping("/find/tag")
    public RestBean<?> get(HttpServletResponse response){
        response.setContentType("application/json;charset=utf-8");

        List<Tag> list = server.getTagList();
        if(list!= null){
            return RestBean.success(list);
        }
        else return RestBean.db_un_failure("获取失败");
    }

    @ResponseBody
    @RequestMapping("/delTag")
    public RestBean<?> delete(HttpServletResponse response,Integer tid){
        response.setContentType("application/json;charset=utf-8");

        System.out.println(tid);

        if(server.deleteTagByTid(tid) ==1 ){
            return RestBean.success("删除成功");
        }
        else return RestBean.db_un_failure("删除失败");
    }

    @ResponseBody
    @RequestMapping("/limitTag")
    public RestBean<?> limit(HttpServletResponse response,Integer page,Integer limit){
        response.setContentType("application/json;charset=utf-8");

        page--;
        if(page>=1){
            page = (page) * 10;
            limit += page;
        }

        List<Tag> list = server.limitTag(page,limit);
        if(list!= null){
            return RestBean.success(list);
        }
        else return RestBean.db_un_failure("获取失败");
    }

    @ResponseBody
    @RequestMapping("/TagSearch")
    public RestBean<?> find(HttpServletResponse response,String text){
        response.setContentType("application/json;charset=utf-8");

        List<Tag> list = server.getTagText(text);
        if(list!= null){
            return RestBean.success(list);
        }
        else return RestBean.db_un_failure("获取失败");
    }

    @ResponseBody
    @RequestMapping("/getTagCount")
    public RestBean<?> count(HttpServletResponse response){
        response.setContentType("application/json;charset=utf-8");

        Integer count = server.getTagCount();
        if(count!= null){
            return RestBean.success(count);
        }
        else return RestBean.db_un_failure("获取失败");
    }

}
