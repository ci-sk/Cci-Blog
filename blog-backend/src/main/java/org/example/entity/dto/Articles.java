package org.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.example.entity.BaseData;
import org.example.entity.vo.response.ArticlesVO;

import java.util.Date;
import java.util.List;

@Data
@TableName("db_articles")
@AllArgsConstructor
@NoArgsConstructor
public class Articles implements BaseData {
//    @TableId(type = IdType.AUTO)
    private Integer aid;
    private Integer uid;
    private String title;
    private String content;
    private int del;
    private Date publish_Time;
}


