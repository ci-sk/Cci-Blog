package org.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.RestBean;
import org.example.entity.dto.Account;
import org.example.entity.vo.response.AccountVO;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 账户控制器
 * 该类负责处理与账户相关的HTTP请求，包括添加、查询、删除和分页查询账户等操作。
 */
@Controller
@RequestMapping("/api")
public class AccountController {

    @Autowired
    AccountService service;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 添加账户
     * @param response HttpServletResponse 对象，用于设置响应内容类型和字符编码
     * @param username 用户名
     * @param email 电子邮件
     * @return RestBean<?> 对象，包含添加账户的结果
     */
    @ResponseBody
    @PostMapping("/auth/addAccount")
    public RestBean<?> insertAccount(HttpServletResponse response,
                                     @RequestParam(required = false) Long id,
                                     @RequestParam String username,
                                     @RequestParam String email,
                                     @RequestParam String avatar,
                                     @RequestParam String website)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        System.out.println("添加" + username+" "+email+" "+avatar+" "+website);
        Account account = new Account();
        account.setUsername(username)
                .setPassword(passwordEncoder.encode("123456"))
                .setEmail(email)
                .setRole("user")
                .setTime(new Date())
                .setAvatar(avatar)
                .setWebsite(website);
        if(id != null){
            account.setUid(id);
        }
        if (service.insertAccount(account) == 1) {
            return RestBean.db_add_success(account, "添加成功");
        } else {
            return RestBean.db_failure();
        }
    }

    /**
     * 删除账户
     * @param response HttpServletResponse 对象，用于设置响应内容类型和字符编码
     * @param uid 用户 ID
     * @return RestBean<?> 对象，包含删除账户的结果
     */
    @ResponseBody
    @RequestMapping("/delAccount")
    public RestBean<?> deleteAccount(HttpServletResponse response,
                                     @RequestParam("uid") Long uid)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (service.deleteAccount(uid) == 1) {
            return RestBean.db_success("删除成功");
        } else {
            return RestBean.db_un_failure("删除失败");
        }
    }

    /**
     * 查询账户
     * @param response HttpServletResponse 对象，用于设置响应内容类型和字符编码
     * @return RestBean<?> 对象，包含查询账户的结果
     */
    @ResponseBody
    @GetMapping("/getAll/Account")
    public RestBean<?> findAccount(HttpServletResponse response)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        List<Account> accounts = service.getAccountCount();
        ArrayList<AccountVO> vo = new ArrayList<>();
        for (Account a : accounts) {
            AccountVO vo1 = (a.asViewObject(AccountVO.class, v -> v.setUsername(a.getUsername())
                .setEmail(a.getEmail())
                .setRole(a.getRole())
                .setTime(a.getTime())
                .setAvatar(a.getAvatar())
                .setWebsite(a.getWebsite())));
            vo.add(vo1);
        }
        return RestBean.success(accounts);
    }


    @ResponseBody
    @RequestMapping("/getById/Account")
    public RestBean<?> findAccount(HttpServletResponse response,
                                   String text){
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
         Account ac = service.findAccountByNameOrEmail(text);
        if (ac != null) {
            ac.setPassword("");
            return RestBean.success(ac);
        }
        else {
            return RestBean.db_un_failure("未查询到");
        }
    }

    /**
     * 分页查询账户
     * @param response HttpServletResponse 对象，用于设置响应内容类型和字符编码
     * @param page 页码
     * @param limit 每页数量
     * @return RestBean<?> 对象，包含分页查询账户的结果
     */
    @ResponseBody
    @RequestMapping("/getLimit/Account")
    public RestBean<?> getAccountLimit(HttpServletResponse response,
                                       @RequestParam(required = false) String text,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer limit)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        List<Account> account = service.limitAccount(text,page, limit);
        return RestBean.success(account);
    }

    /**
     * 获取账户数量
     * @param response HttpServletResponse 对象，用于设置响应内容类型和字符编码
     * @return RestBean<?> 对象，包含获取账户数量的结果
     */
    @ResponseBody
    @RequestMapping("/getCount/Account")
    public RestBean<?> getAccountCount(HttpServletResponse response)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Integer count = service.getCount();
        return RestBean.success(count);
    }
}
