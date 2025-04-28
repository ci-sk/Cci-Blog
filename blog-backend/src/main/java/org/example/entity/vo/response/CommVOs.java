package org.example.entity.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class CommVOs implements BaseCommentVO<CommVOs>{
    private Integer cid;
    private Integer aid;
    private Long uid;
    private String website;
    private String avatar;
    private String email;
    private Integer reply_cid;
    private String username;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
    private List<CommVOs> children; // 新增属性，用于存储子评论列表

    @Override
    public void setChildren(List<CommVOs> children) {
        this.children = children;
    }
}
