package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.dto.Tag;
import org.example.mapper.TagMapper;
import org.example.service.TagServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagServer {


    @Autowired
    TagMapper mapper;

    @Override
    public List<Tag> getTagList() {
        return mapper.getTagList();
    }

    @Override
    public int addTag(Tag tag) {
        return mapper.addTag(tag);
    }

    @Override
    public int deleteTagByTid(int tid) {
        return mapper.deleteTagByTid(tid);
    }
}
