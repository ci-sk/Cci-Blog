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
    private String username;
    private Integer reply_cid;
    private Integer aid;
    private String content;
    private Date c_time;
    private Comments reply;
    private Articles article;
}
