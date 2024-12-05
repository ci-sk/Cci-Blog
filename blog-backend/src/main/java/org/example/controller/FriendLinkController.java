package org.example.controller;

import org.example.entity.dto.FriendLink;
import org.example.entity.RestBean;
import org.example.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FriendLinkController {

    @Autowired
    FriendLinkService friendLinkService;

    // 查询所有友链
    @ResponseBody
    @GetMapping("/getAll/FriendLink")
    public RestBean<?> getAllFriendLinks(HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");

        List<FriendLink> friendLinks = friendLinkService.getAllFriendLinks();
        return RestBean.success(friendLinks);
    }

    // 分页查询友链
    @ResponseBody
    @PutMapping("/getLimit/FriendLink")
    public RestBean<?> getLimitFriendLinks(HttpServletResponse response,
            @RequestParam(required = false, defaultValue = "") String text,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        response.setContentType("application/json;charset=utf-8");
        try {
            List<FriendLink> friendLinks = friendLinkService.getFriendLinksWithLimit(text, page, size);
            return RestBean.success(friendLinks);
        } catch (Exception e) {
            return RestBean.failure(500, "服务器错误：" + e.getMessage());
        }
    }

    // 获取友链数量
    @ResponseBody
    @GetMapping("/getCount/FriendLink")
    public RestBean<?> getFriendLinkCount(HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");

        Integer count = friendLinkService.getFriendLinkCount();
        return RestBean.success(count);
    }

    // 搜索友链
    @ResponseBody
    @GetMapping("/getSearch/FriendLink")
    public RestBean<?> searchFriendLinks(HttpServletResponse response, @RequestParam String keyword) {
        response.setContentType("application/json;charset=utf-8");

        List<FriendLink> friendLinks = friendLinkService.searchFriendLinks(keyword);
        return RestBean.success(friendLinks);
    }

    // 根据ID获取友链
    @ResponseBody
    @GetMapping("/getFriendLink/{id}")
    public RestBean<?> getFriendLinkById(@PathVariable Integer id) {

        FriendLink friendLink = friendLinkService.getFriendLinkById(id);
        if (friendLink != null) {
            return RestBean.success(friendLink);
        } else {
            return RestBean.failure(404, "友链未找到");
        }
    }

    // 添加或更新友链
    @ResponseBody
    @PutMapping("/saveFriendLink")
    public RestBean<?> saveFriendLink(HttpServletResponse response, @RequestBody FriendLink friendLink) {
        response.setContentType("application/json;charset=utf-8");

            boolean success;
            String message;
        System.out.println(friendLink);
            
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
                return RestBean.failure(400, friendLink.getFid() == null ? "友链添加失败" : "友链更新失败");
            }
    }

    // 删除友链
    @ResponseBody
    @DeleteMapping("/delFriendLink")
    public RestBean<?> deleteFriendLink(HttpServletResponse response, @RequestBody Map<String, Integer> param) {
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