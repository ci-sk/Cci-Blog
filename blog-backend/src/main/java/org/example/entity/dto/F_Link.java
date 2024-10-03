package org.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("db_f_link")
@AllArgsConstructor
@NoArgsConstructor
public class F_Link {
    @TableId(type = IdType.AUTO)
    private Integer fid;
    private String fname;
    private String describes;
    private String url;
    private String icon;
}
