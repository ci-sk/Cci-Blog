package org.example.entity.vo.response;

import java.util.List;

public interface BaseCommentVO<T> {
    Integer getCid();
    Integer getReply_cid();
    void setChildren(List<T> children);
}