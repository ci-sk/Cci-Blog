package org.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@TableName("db_tag")
@Data
@AllArgsConstructor
public class Tag {
    @TableId(type = IdType.AUTO)
    private Integer tid;
    private Integer aid;
    private String tag;
    private String desc;
}
