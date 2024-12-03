package org.example.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@TableName("db_category")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id; // 分类ID
    private String name; // 分类名称
    private String description; // 分类描述
}