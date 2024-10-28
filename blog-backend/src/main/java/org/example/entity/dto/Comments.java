package org.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.BaseData;

import java.util.Date;

@Data
@TableName("db_comments")
@AllArgsConstructor
@NoArgsConstructor
public class Comments implements BaseData {
    @TableId(type = IdType.AUTO)
    private Integer cid;
    private Integer uid;
    private Integer reply_uid;
    private Integer aid;
    private String content;
    private Date c_time;
    private Account account;
    private Account replyAccount;
    private Articles article;
}
