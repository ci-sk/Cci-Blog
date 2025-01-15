package org.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.RestBean;
import org.example.entity.dto.Tag;
import org.example.service.impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 标签控制器
 * 该类负责处理与标签相关的HTTP请求，包括添加、查询、删除和分页查询标签等操作。
 */
@Controller
@RequestMapping("/api")
public class TagController {

    @Autowired
    TagServiceImpl server;

    /**
     * 添加标签
     * @param response 响应
     * @param tagName 标签名
     * @return 响应结果
     */
    @ResponseBody
    @RequestMapping("/addTag")
    public RestBean<?> add(HttpServletResponse response,
                           @RequestParam String tagName)
    {
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

    /**
     * 查询标签
     * @param response 响应
     * @return 响应结果
     */
    @ResponseBody
    @RequestMapping("/getAll/tag")
    public RestBean<?> get(HttpServletResponse response)
    {
        response.setContentType("application/json;charset=utf-8");

        List<Tag> list = server.getTagList();
        if(list!= null){
            return RestBean.success(list);
        }
        else return RestBean.db_un_failure("获取失败");
    }

    /**
     * 删除标签
     * @param response 响应
     * @param tid 标签id
     * @return 响应结果
     */
    @ResponseBody
    @RequestMapping("/delTag")
    public RestBean<?> delete(HttpServletResponse response,Integer tid)
    {
        response.setContentType("application/json;charset=utf-8");

        if(server.deleteTagByTid(tid) ==1 ){
            return RestBean.success("删除成功");
        }
        else return RestBean.db_un_failure("删除失败");
    }

    /**
     * 分页查询标签
     * @param response 响应
     * @param page 页码
     * @param limit 每页数量
     * @return 响应结果
     */
    @ResponseBody
    @RequestMapping("/getLimit/Tag")
    public RestBean<?> limit(HttpServletResponse response,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit)
    {
        response.setContentType("application/json;charset=utf-8");

        List<Tag> list = server.limitTag(page,limit);
        if(list!= null){
            return RestBean.success(list);
        }
        else return RestBean.db_un_failure("获取失败");
    }

    /**
     * 标签搜索
     * @param response 响应
     * @param text 搜索文本
     * @return 响应结果
     */
    @ResponseBody
    @RequestMapping("/getSearch/Tag")
    public RestBean<?> find(HttpServletResponse response,@RequestParam String text)
    {
        response.setContentType("application/json;charset=utf-8");

        List<Tag> list = server.getTagText(text);
        if(list!= null){
            return RestBean.success(list);
        }
        else return RestBean.db_un_failure("获取失败");
    }

    /**
     * 获取标签数量
     * @param response 响应
     * @return 响应结果
     */
    @ResponseBody
    @RequestMapping("/getCount/Tag")
    public RestBean<?> count(HttpServletResponse response)
    {
        response.setContentType("application/json;charset=utf-8");

        Integer count = server.getTagCount();
        if(count!= null){
            return RestBean.success(count);
        }
        else return RestBean.db_un_failure("获取失败");
    }
}
