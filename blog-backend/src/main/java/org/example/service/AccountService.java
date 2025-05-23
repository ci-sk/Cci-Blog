package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.dto.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByNameOrEmail(String text);

    int insertAccount(Account account);

    int deleteAccount(Long uid);

    List<Account> getAccountCount();

    List<Account> limitAccount(String text,Integer page,Integer limit);

    Integer getCount();
}
