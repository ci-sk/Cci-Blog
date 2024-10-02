package org.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("db_comments")
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    @TableId(type = IdType.AUTO)
    private Integer cid;
    private Integer uid;
    private Integer aid;
    private String content;
    private Date c_time;
    private Account account;


}
