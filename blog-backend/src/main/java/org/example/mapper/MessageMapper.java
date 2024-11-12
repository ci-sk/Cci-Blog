package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entity.dto.Message;

import java.util.List;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
    int  addMessage(Message message);

    List<Message> getMessage(String content, Integer page, Integer limit);;

    int deleteMessage(int mid);

    int getMessageCount();
}
