package org.example.entity.vo.response;

import lombok.Data;
import org.example.entity.BaseData;

import java.util.Date;

@Data
public class CommentsVO{
    private Integer cid;
    private Integer aid;
    private Integer reply_cid;
    private String title;
    private String username;
    private String reply_username;
    private String content;
    private Date time;
}
