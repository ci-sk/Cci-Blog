package org.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("db_message")
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @TableId(type = IdType.AUTO)
    private Integer mid;
    private String username;
    private String content;
    private Date time;
}
