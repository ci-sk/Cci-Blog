package org.example.entity.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class CommentsVO implements BaseCommentVO<CommentsVO>{
    private Integer cid;
    private Integer aid;
    private Integer reply_cid;
    private String title;
    private String username;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
    private List<CommentsVO> children; // 新增属性，用于存储子评论列表

    @Override
    public void setChildren(List<CommentsVO> children) {
        this.children = children;
    }
}
