package org.example.entity.vo.response;

import lombok.Data;
import org.example.entity.BaseData;

import java.util.Date;
import java.util.List;

@Data
public class ArticlesVO{
    Integer aid;
    String title;
    String content;
    String tags;
    String desc;
    String img_url;
    Date time;
    Integer del;
}
