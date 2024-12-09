package com.example.demo.services;

import com.example.demo.dto.FriendshipDTO;
import com.example.demo.model.Friendship;

import java.util.List;

public interface FriendshipService {

    List<Friendship> findFriendshipByFirstUserId(Long firstUserId);
    List<Friendship> findFriendsByFirstUserIdAndStatus(Long firstUserId, String status);

    List<Friendship> createFriendshipRequest(FriendshipDTO friendshipDTO);
    List<Friendship> updateFriendshipStatus(Long friendshipId);
    void deleteFriendshipById(Long friendshipId);

    Friendship findFriendshipByFirstUserIdAndSecondUserId(Long firstUserId, Long secondUserId);

}
