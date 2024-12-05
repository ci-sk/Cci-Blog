package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.example.entity.dto.FriendLink;

import java.util.List;

@Mapper
public interface FriendLinkMapper extends BaseMapper<FriendLink>{

    List<FriendLink> getAllFriendLinks();

    List<FriendLink> getFriendLinksWithLimit(String text,int page, int size);

    List<FriendLink> searchFriendLinks(String keyword);

    FriendLink getFriendLinkById(Integer id);

    int getCountFriendLink();

    int addFriendLink(FriendLink friendLink);

    int updateFriendLink(FriendLink friendLink);

    int deleteFriendLink(Integer id);
} 