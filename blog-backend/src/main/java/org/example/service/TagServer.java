package org.example.service;

import org.example.entity.dto.Tag;

import java.util.List;

public interface TagServer {
    List<Tag> getTagList();

   int addTag(Tag tag);

    int deleteTagByTid(int tid);
}
