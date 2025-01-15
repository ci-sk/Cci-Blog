package org.example.entity.vo.request;

import lombok.Data;

@Data
public class CommentsRequest {
    private Integer aid;
    private String content;
    private String username;
}
