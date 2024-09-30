package org.example.entity.vo.response;

import lombok.Data;
import org.example.entity.BaseData;

import java.util.Date;
import java.util.List;

@Data
public class ArticlesVO implements BaseData {
    Integer aid;
    String title;
    String content;
    Date publish_Time;
    Integer del;
}
