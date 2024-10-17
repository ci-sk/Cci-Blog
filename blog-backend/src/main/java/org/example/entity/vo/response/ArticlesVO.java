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
    Integer uid;
    String img_url;
    Date publish_Time;
    Integer del;
}
