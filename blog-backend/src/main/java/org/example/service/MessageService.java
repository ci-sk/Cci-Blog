package org.example.service;


import org.example.entity.dto.Message;

import java.util.List;

public interface MessageService {
    int  addMessage(Message message);

    List<Message> getMessage(String content, Integer page, Integer limit);;

    int deleteMessage(int mid);

    int getMessageCount();

    int countUnreadMessages();

    List<Message> getRecentMessages(int limit);

    void markAllAsRead();
}
