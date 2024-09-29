package org.example.entity.vo.response;

import lombok.Data;

import java.util.Date;

@Data
public class ArticlesVO {
    String title;
    String content;
    Date publish_Time;
}
