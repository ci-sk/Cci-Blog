package org.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@TableName("db_f_link")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class FriendLink {
    @TableId(type = IdType.AUTO)
    private Integer fid;
    private String fname;
    private String describes;
    private String url;
    private String icon;
}
