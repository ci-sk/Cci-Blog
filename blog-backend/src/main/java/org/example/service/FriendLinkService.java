package org.example.service;

import org.example.entity.dto.FriendLink;
import java.util.List;

public interface FriendLinkService {

    List<FriendLink> getAllFriendLinks();

    FriendLink getFriendLinkById(Integer id);

    boolean addFriendLink(FriendLink friendLink);

    boolean updateFriendLink(Integer id, FriendLink friendLink);

    boolean deleteFriendLink(Integer id);

    List<FriendLink> getFriendLinksWithLimit(String text,int page, int size);

    int getFriendLinkCount();

    List<FriendLink> searchFriendLinks(String keyword);
} 