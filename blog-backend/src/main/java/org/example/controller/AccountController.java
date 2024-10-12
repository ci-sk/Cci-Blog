package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.annotations.Delete;
import org.example.entity.RestBean;
import org.example.entity.dto.Account;
import org.example.entity.vo.response.AccountVO;
import org.example.entity.vo.response.ArticlesVO;
import org.example.service.AccountService;
import org.example.utils.TimeFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api")
public class AccountController {

    @Autowired
    AccountService service;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @ResponseBody
    @PutMapping("/addAccount")
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
    @RequestMapping("/delAccount")
    public RestBean<?> deleteAccount(HttpServletResponse response,Integer uid)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        System.out.println("删除"+uid);
        if(service.deleteAccount(uid) == 1)
        {
            return RestBean.db_success("删除成功");
        }else {
            return  RestBean.db_un_failure("删除失败");
        }
    }

    @ResponseBody
    @GetMapping("/find/Account")
    public RestBean<?> findAccount(HttpServletResponse response)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Account> accounts = service.getAccountCount();

        ArrayList<AccountVO> vo = new ArrayList<>();;
        for(Account  a : accounts){
            AccountVO vo1 =(a.asViewObject(AccountVO.class,v->{
                v.setUsername(a.getUsername());
                v.setEmail(a.getEmail());
                v.setRole(a.getRole());
//                System.out.println(a.getRegister_Time().getTime());
//                v.setRegister_Time(TimeFormatUtil.formatTimestamp(a.getRegister_Time().getTime()));
            }));
            vo.add(vo1);
        }

        return RestBean.success(accounts);
    }

    @ResponseBody
    @RequestMapping("/getAccountByText")
    public RestBean<?> getAccountByText(HttpServletResponse response,String text)
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        System.out.println(text);

        List<Account> account = service.getAccountByText(text);

        return RestBean.success(account);
    }


    @ResponseBody
    @RequestMapping("/getAccountLimit")
    public RestBean<?> getAccountLimit(HttpServletResponse response, HttpServletRequest request, Integer page, Integer limit)
    {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        page--;
        if(page>=1){
            page = (page) * 10;
            limit += page;
        }

        System.out.println(page+" "+limit);

        List<Account> account = service.limitAccount(page,limit);

        return RestBean.success(account);
    }

}
