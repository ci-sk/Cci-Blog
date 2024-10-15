package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entity.dto.Tag;

import java.util.List;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    List<Tag> getTagList();

    int addTag(Tag tag);

    int deleteTagByTid(int tid);

    List<Tag> limitTag(Integer page,Integer limit);

    List<Tag> getTagText(String text);

    Integer getTagCount();
}
