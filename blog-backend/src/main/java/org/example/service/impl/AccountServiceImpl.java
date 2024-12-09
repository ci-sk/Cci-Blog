package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.entity.dto.Account;
import org.example.mapper.AccountMapper;
import org.example.service.AccountService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Resource
    AccountMapper mapper;

    /**
     * 根据用户名加载用户详细信息
     *
     * @param username 用户名
     * @return 用户详细信息
     * @throws UsernameNotFoundException 如果用户名不存在
     */
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
               .password(account.getPassword())
               .roles(account.getRole())
               .build();
    }

    /**
     * 根据用户名或邮箱查找账户
     *
     * @param text 用户名或邮箱
     * @return 账户信息
     */
    public Account findAccountByNameOrEmail(String text) {
        return mapper.findAccountByNameOrEmail(text);
    }

    /**
     * 插入新账户
     *
     * @param account 账户信息
     * @return 插入结果
     */
    @Override
    public int insertAccount(Account account) {
       return mapper.insertAccount(account);
    }

    /**
     * 根据用户 ID 删除账户
     *
     * @param uid 用户 ID
     * @return 删除结果
     */
    @Override
    public int deleteAccount(Integer uid) {
        return mapper.deleteAccount(uid);
    }

    /**
     * 获取账户数量
     *
     * @return 账户数量
     */
    @Override
    public List<Account> getAccountCount() {
        return mapper.getAccountCount();
    }

    /**
     * 分页查询账户
     *
     * @param page 页码
     * @param limit 每页限制
     * @return 账户列表
     */
    @Override
    public List<Account> limitAccount(String text,Integer page, Integer limit) {
        return mapper.limitAccount(text,page, limit);
    }

    /**
     * 获取账户总数
     *
     * @return 账户总数
     */
    @Override
    public Integer getCount() {
        return mapper.getCount();
    }
}
