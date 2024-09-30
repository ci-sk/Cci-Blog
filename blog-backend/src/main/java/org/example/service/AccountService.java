package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.dto.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByNameOrEmail(String text);

    public int insertAccount(Account account);

    public int deleteAccount(Integer uid);

    public List<Account> getAccountCount();
}
