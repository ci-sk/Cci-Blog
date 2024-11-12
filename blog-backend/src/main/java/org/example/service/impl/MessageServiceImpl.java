package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.dto.Message;
import org.example.mapper.MessageMapper;
import org.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    MessageMapper mapper;

    @Override
    public int addMessage(Message message) {
        return mapper.addMessage(message);
    }

    @Override
    public List<Message> getMessage(String content, Integer page, Integer limit) {
        return mapper.getMessage(content, page, limit);
    }

    @Override
    public int deleteMessage(int mid) {
        return mapper.deleteMessage(mid);
    }

    @Override
    public int getMessageCount() {
        return mapper.getMessageCount();
    }
}
