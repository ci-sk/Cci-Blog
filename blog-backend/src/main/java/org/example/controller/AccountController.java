package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
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
    @PutMapping("/addAccount")
    public RestBean<?> insertAccount(HttpServletResponse response, String username, String email)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Account account = new Account();
        account.setUsername(username)
                .setUsername(passwordEncoder.encode("123456"))
                .setEmail(email)
                .setRole("user")
                .setTime(new Date());

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
    public RestBean<?> deleteAccount(HttpServletResponse response, Integer uid)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        System.out.println("删除" + uid);
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
            AccountVO vo1 = (a.asViewObject(AccountVO.class, v -> {
                v.setUsername(a.getUsername());
                v.setEmail(a.getEmail());
                v.setRole(a.getRole());
            }));
            vo.add(vo1);
        }

        return RestBean.success(accounts);
    }

    /**
     * 根据文本搜索账户
     * @param response HttpServletResponse 对象，用于设置响应内容类型和字符编码
     * @param text 搜索文本
     * @param page 页码
     * @param limit 每页数量
     * @return RestBean<?> 对象，包含搜索账户的结果
     */
    @ResponseBody
    @RequestMapping("/getAccountByText")
    public RestBean<?> getAccountByText(HttpServletResponse response, String text, Integer page, Integer limit)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        page--;
        if (page >= 1) {
            page = (page) * 10;
            limit += page;
        }

        List<Account> account = service.getAccountByText(text, page, limit);

        return RestBean.success(account);
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
    public RestBean<?> getAccountLimit(HttpServletResponse response, Integer page, Integer limit)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        page--;
        if (page >= 1) {
            page = (page) * 10;
            limit += page;
        }

        List<Account> account = service.limitAccount(page, limit);

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
