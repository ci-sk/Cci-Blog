package org.example.entity.vo.response;

import lombok.Data;
import lombok.experimental.Accessors;
import org.example.entity.BaseData;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class ArticlesVO{
    private Integer aid;
    private String title;
    private String content;
    private String tags;
    private String desc;
    private String img_url;
    private Date time;
    private  Integer del;
}
