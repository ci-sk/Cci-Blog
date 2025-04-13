package org.example.entity.vo.request;

import lombok.Data;

@Data
public class CommentsRequest {
    private Integer reply_cid;
    private Integer aid;
    private Long uid;
    private String content;
}
