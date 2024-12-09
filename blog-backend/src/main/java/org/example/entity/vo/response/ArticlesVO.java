package org.example.entity.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
    private Integer categoryId;
    private Category category;
}
