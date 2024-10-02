package org.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.entity.BaseData;

import java.util.Date;

@Data
@TableName("db_account")
@AllArgsConstructor
public class Account implements BaseData {
    @TableId(type = IdType.AUTO)
    private Integer uid;
    private String username;
    private String password;
    private String email;
    private String role;
    private Date register_Time;

    public Account(String username, String password,String email, String role, Date register_Time) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.register_Time = register_Time;
    }

    public Account() {

    }
}
