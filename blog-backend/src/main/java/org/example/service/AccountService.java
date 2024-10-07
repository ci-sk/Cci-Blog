package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.dto.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByNameOrEmail(String text);

    int insertAccount(Account account);

    int deleteAccount(Integer uid);

    List<Account> getAccountCount();

    List<Account> getAccountByText(String text);

    List<Account> limitAccount(Integer page,Integer limit);
}
