package org.example.entity.vo.response;

import lombok.Data;
import lombok.experimental.Accessors;
import org.example.entity.dto.Category;

import java.util.Date;

/**
 * 
 */
@Data
@Accessors(chain = true)
public class ArticlesVO{
    private Integer aid;
    private String title;
    private String content;
    private String desc;
    private String tags;
    private String img_url;
    private Integer del;
    private Date time;
    private Integer categoryId;
    private Category category;
}
