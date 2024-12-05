package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.entity.dto.FriendLink;
import org.example.mapper.FriendLinkMapper;
import org.example.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkMapper,FriendLink> implements FriendLinkService {

    @Autowired
    FriendLinkMapper mapper;

    @Override
    public List<FriendLink> getAllFriendLinks() {
        return mapper.getAllFriendLinks();
    }

    @Override
    public FriendLink getFriendLinkById(Integer id) {
        return mapper.getFriendLinkById(id);
    }

    @Override
    public boolean addFriendLink(FriendLink friendLink) {
        return mapper.addFriendLink(friendLink) > 0;
    }

    @Override
    public boolean updateFriendLink(Integer id, FriendLink friendLink) {
        friendLink.setFid(id);
        return mapper.updateFriendLink(friendLink) > 0;
    }

    @Override
    public boolean deleteFriendLink(Integer id) {
        return mapper.deleteFriendLink(id) > 0;
    }

    @Override
    public List<FriendLink> getFriendLinksWithLimit(String text,int page, int size) {
        // 实现分页逻辑
        return mapper.getFriendLinksWithLimit(text,page, size);
    }

    @Override
    public int getFriendLinkCount() {
        // 实现获取数量逻辑
        return mapper.getCountFriendLink();
    }

    @Override
    public List<FriendLink> searchFriendLinks(String keyword) {
        // 实现搜索逻辑
        return List.of();
    }
} 