package org.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@TableName("db_comments")
@AllArgsConstructor
public class Comments {
    @TableId(type = IdType.AUTO)
    private Integer cid;
    private Integer aid;
    private String txt;
    private Date c_time;

}
