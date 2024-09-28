package org.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@TableName("db_message")
@AllArgsConstructor
public class message {
    @TableId(type = IdType.AUTO)
    private Integer mid;
    private Integer uid;
    private String content;
    private Date m_time;
}
