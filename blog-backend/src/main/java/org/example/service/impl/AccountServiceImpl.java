package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.entity.dto.Account;
import org.example.mapper.AccountMapper;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Autowired
    AccountMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.findAccountByNameOrEmail(username);
        if(account == null)
        {
            System.out.println("用户名不存在");
            throw new UsernameNotFoundException("用户名或者密码错误");
        }
        return User
                .withUsername(username)
                .roles(account.getRole())
                .password(account.getPassword())
                .build();
    }

    public Account findAccountByNameOrEmail(String text) {
        return this.query()
                .eq("username",text).or()
                .eq("email",text)
                .one();
    }

    @Override
    public int insertAccount(Account account) {
       return mapper.insertAccount(account);
    }

    @Override
    public int deleteAccount(Integer uid) {
        return mapper.deleteAccount(uid);
    }

    @Override
    public List<Account> getAccountCount() {
        return mapper.getAccountCount();
    }
}
