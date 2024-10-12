package org.example.service;

import org.example.entity.dto.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getTagList();

   int addTag(Tag tag);

    int deleteTagByTid(int tid);

    List<Tag> limitTag(Integer page,Integer limit);

    List<Tag> getTagText(String text);

}
