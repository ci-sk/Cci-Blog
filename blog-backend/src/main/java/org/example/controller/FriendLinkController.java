package org.example.controller;

import org.example.entity.dto.FriendLink;
import org.example.entity.RestBean;
import org.example.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 友链控制器，处理与友链相关的请求
 */
@RestController
@RequestMapping("/api")
public class FriendLinkController {

    @Autowired
    FriendLinkService friendLinkService;

    /**
     * 获取所有友链
     *
     * @param response HTTP 响应对象
     * @return 包含所有友链的响应对象
     */
    @ResponseBody
    @GetMapping("/getAll/FriendLink")
    public RestBean<?> getAllFriendLinks(HttpServletResponse response)
    {
        response.setContentType("application/json;charset=utf-8");

        List<FriendLink> friendLinks = friendLinkService.getAllFriendLinks();
        return RestBean.success(friendLinks);
    }

    /**
     * 分页查询友链
     *
     * @param response HTTP 响应对象
     * @param text     友链名称关键字
     * @param page     页码
     * @param limit    每页大小
     * @return 分页友链的结果
     */
    @ResponseBody
    @PutMapping("/getLimit/FriendLink")
    public RestBean<?> getLimitFriendLinks(HttpServletResponse response,
                                           @RequestParam(required = false) String text,
                                           @RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer limit)
    {
        response.setContentType("application/json;charset=utf-8");
        try {
            List<FriendLink> friendLinks = friendLinkService.getFriendLinksWithLimit(text, page, limit);
            return RestBean.success(friendLinks);
        } catch (Exception e) {
            return RestBean.failure(500, "服务器错误：" + e.getMessage());
        }
    }

    /**
     * 获取友链数量
     *
     * @param response HTTP 响应对象
     * @return 友链数量
     */
    @ResponseBody
    @GetMapping("/getCount/FriendLink")
    public RestBean<?> getFriendLinkCount(HttpServletResponse response)
    {
        response.setContentType("application/json;charset=utf-8");

        Integer count = friendLinkService.getFriendLinkCount();
        return RestBean.success(count);
    }

    /**
     * 搜索友链
     *
     * @param response HTTP 响应对象
     * @param keyword  搜索关键字
     * @return 搜索结果
     */
    @ResponseBody
    @GetMapping("/getSearch/FriendLink")
    public RestBean<?> searchFriendLinks(HttpServletResponse response, @RequestParam String keyword)
    {
        response.setContentType("application/json;charset=utf-8");

        List<FriendLink> friendLinks = friendLinkService.searchFriendLinks(keyword);
        return RestBean.success(friendLinks);
    }

    /**
     * 根据 ID 获取友链
     *
     * @param id 友链 ID
     * @return 友链对象
     */
    @ResponseBody
    @GetMapping("/getFriendLink/{id}")
    public RestBean<?> getFriendLinkById(@PathVariable Integer id)
    {

        FriendLink friendLink = friendLinkService.getFriendLinkById(id);
        if (friendLink!= null) {
            return RestBean.success(friendLink);
        } else {
            return RestBean.failure(404, "友链未找到");
        }
    }

    /**
     * 添加或更新友链
     *
     * @param response HTTP 响应对象
     * @param friendLink 友链对象
     * @return 添加或更新结果
     */
    @ResponseBody
    @PutMapping("/saveFriendLink")
    public RestBean<?> saveFriendLink(HttpServletResponse response, @RequestBody FriendLink friendLink)
    {
        response.setContentType("application/json;charset=utf-8");

        boolean success;
        String message;

        if (friendLink.getFid() == null) {
            // 添加友链
            success = friendLinkService.addFriendLink(friendLink);
            message = "友链添加成功";
        } else {
            // 更新友链
            success = friendLinkService.updateFriendLink(friendLink.getFid(), friendLink);
            message = "友链更新成功";
        }

        if (success) {
            return RestBean.success(message);
        } else {
            return RestBean.failure(400, friendLink.getFid() == null? "友链添加失败" : "友链更新失败");
        }
    }

    /**
     * 删除友链
     *
     * @param response HTTP 响应对象
     * @param param    包含友链 ID 的参数
     * @return 删除结果
     */
    @ResponseBody
    @DeleteMapping("/delFriendLink")
    public RestBean<?> deleteFriendLink(HttpServletResponse response, @RequestBody Map<String, Integer> param)
    {
        response.setContentType("application/json;charset=utf-8");

        try {
            Integer id = param.get("id");
            if (id == null) {
                return RestBean.failure(400, "缺少必要的id参数");
            }
            boolean success = friendLinkService.deleteFriendLink(id);
            if (success) {
                return RestBean.success("友链删除成功");
            } else {
                return RestBean.failure(400, "友链删除失败");
            }
        } catch (Exception e) {
            return RestBean.failure(500, "服务器错误：" + e.getMessage());
        }
    }
}
