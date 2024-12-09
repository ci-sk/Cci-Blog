package org.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@TableName("db_message")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @TableId(type = IdType.AUTO)
    private Integer mid;
    private String username;
    private String content;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    private Integer isRead = 0;  // 0: 未读, 1: 已读
}
