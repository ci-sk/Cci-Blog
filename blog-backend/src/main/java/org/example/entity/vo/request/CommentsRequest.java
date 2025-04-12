package org.example.entity.vo.request;

import lombok.Data;

@Data
public class CommentsRequest {
    private Integer aid;
    private Integer uid;
    private String content;
}
