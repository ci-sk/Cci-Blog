package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.entity.dto.Message;

import java.util.List;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
    int  addMessage(Message message);

    List<Message> getMessage(String content, Integer page, Integer limit);;

    int deleteMessage(int mid);

    int getMessageCount();


     @Select("SELECT COUNT(*) FROM db_message WHERE is_read = #{isRead}")
    int countByIsRead(int isRead);
    
    @Select("SELECT * FROM db_message ORDER BY time DESC LIMIT #{limit}")
    List<Message> findRecentMessages(int limit);
    
    @Update("UPDATE db_message SET is_read = 1 WHERE is_read = 0")
    void updateAllToRead();
}
