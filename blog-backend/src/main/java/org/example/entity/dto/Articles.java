package org.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.example.entity.BaseData;

import java.util.Date;

@Data
@TableName("db_articles")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Articles implements BaseData {
    @TableId(value = "aid", type = IdType.AUTO)
    private Integer aid;
    private String title;
    private String content;
    private String desc;
    private String tags;
    private String img_url;
    private Integer del;
    private Integer viewCount;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publish_Time;

    @TableField("category_id")
    private Integer categoryId;

    @TableField(exist = false)
    private Category category;
}
