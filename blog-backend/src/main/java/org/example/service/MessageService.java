package org.example.service;


import org.example.entity.dto.Message;

import java.util.List;

public interface MessageService {
    int  addMessage(Message message);

    List<Message> getMessage();

    int deleteMessage(int mid);

}
