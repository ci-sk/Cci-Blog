package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.dto.Account;

import java.util.List;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    @Select("select * from db_account where username = #{username} or email = #{username}")
    Account findAccountByNameOrEmail(String username);

    int insertAccount(Account account);

    int deleteAccount(Integer uid);

    List<Account> getAccountCount();

    List<Account> getAccountByText(String text,Integer page,Integer limit);

    @Select("select * from db_account limit #{page},#{limit}")
    List<Account> limitAccount(Integer page,Integer limit);

    @Select("select count(*) from db_account")
    Integer getCount();
}
