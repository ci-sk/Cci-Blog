package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entity.dto.Account;

import java.util.List;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {


    int insertAccount(Account account);

    int deleteAccount(Integer uid);

    List<Account> getAccountCount();
}
