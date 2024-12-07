package org.example.entity.vo.request;

import lombok.Data;

@Data
public class ArticleRequest {
    private Integer aid;
    private String title;
    private String content;
    private String desc;
    private String tags;
    private String img_url;
    private Integer del;
    private Integer categoryId;
}
