package org.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.example.entity.BaseData;

import java.util.Date;

@Data
@TableName("db_account")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Account implements BaseData{
    @TableId(type = IdType.AUTO)
    private Integer uid;
    private String username;
    private String password;
    private String email;
    private String role;
    private Date time;
}
