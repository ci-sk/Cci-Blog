package org.example.entity.vo.response;

import lombok.Data;
import org.example.entity.BaseData;

import java.util.Date;

@Data
public class CommentsVO{
    private Integer cid;
    private Integer aid;
    private String title;
    private String username;
    private String content;
    private Date c_time;
}
