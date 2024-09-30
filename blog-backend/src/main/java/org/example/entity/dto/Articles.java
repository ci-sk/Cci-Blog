package org.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.example.entity.BaseData;
import org.example.entity.vo.response.ArticlesVO;

import java.util.Date;
import java.util.List;

@Data
@TableName("db_articles")
@AllArgsConstructor
public class Articles implements BaseData {
//    @TableId(type = IdType.AUTO)
    private Integer aid;
    private Integer uid;
    private String title;
    private String content;
    private int del;
    private Date publish_Time;


    public Articles (Integer uid, String title, String content,Date publish_Time){
        this.uid = uid;
        this.title = title;
        this.content = content;
        this.publish_Time = publish_Time;
    }

    public Articles(Integer aid, String title, String content) {
        this.aid = aid;
        this.title = title;
        this.content = content;
    }
    public Articles(){}

    public Articles(String title, String content, Date date) {
        this.title = title;
        this.content = content;
        this.publish_Time = date;
    }

}


