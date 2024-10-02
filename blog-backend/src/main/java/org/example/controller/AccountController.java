package org.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.annotations.Delete;
import org.example.entity.RestBean;
import org.example.entity.dto.Account;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin/api/account")
public class AccountController {

    @Autowired
    AccountService service;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @ResponseBody
    @PutMapping("/insert")
    public RestBean<?> insertAccount(HttpServletResponse response,String username,String email)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

//        Account account = new Account(username,passwordEncoder.encode("123456"),email,"user",new Date());

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode("123456"));
        account.setEmail(email);
        account.setRole("user");
        account.setRegister_Time(new Date());

        System.out.println(account);

        if(service.insertAccount(account) == 1)
        {
            return RestBean.db_add_success(account,"添加成功");
        }else {
            return RestBean.db_failure();
        }
    }

    @ResponseBody
    @DeleteMapping("/delete")
    public RestBean<?> deleteAccount(HttpServletResponse response,Integer uid)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if(service.deleteAccount(uid) == 1)
        {
            return RestBean.db_success("删除成功");
        }else {
            return  RestBean.db_failure();
        }
    }

    @ResponseBody
    @GetMapping("find")
    public RestBean<?> findAccount(HttpServletResponse response){
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        return RestBean.success(service.getAccountCount());
    }

}
