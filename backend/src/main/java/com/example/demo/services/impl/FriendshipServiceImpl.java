package com.example.demo.services.impl;

import com.example.demo.dto.FriendshipDTO;
import com.example.demo.model.Friendship;
import com.example.demo.model.User;
import com.example.demo.repository.FriendshipRepository;
import com.example.demo.services.FriendshipService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final UserService userService;

    @Autowired
    public FriendshipServiceImpl(FriendshipRepository friendshipRepository, UserService userService) {
        this.friendshipRepository = friendshipRepository;
        this.userService = userService;
    }

    @Override
    public List<Friendship> findFriendshipByFirstUserId(Long firstUserId) {
        return friendshipRepository.findFriendshipByFirstUserId(firstUserId);
    }

    @Override
    public List<Friendship> findFriendsByFirstUserIdAndStatus(Long firstUserId, String status) {
        return friendshipRepository.findFriendshipByFirstUserIdAndStatus(firstUserId, status);
    }


    @Override
    public List<Friendship> createFriendshipRequest(FriendshipDTO friendshipDTO) {
        User user1 = userService.findUserById(friendshipDTO.getFirstUserId());
        User user2 = userService.findUserById(friendshipDTO.getSecondUserId());

        Friendship firstFriendship = Friendship.fromDTO(friendshipDTO, user1, user2);
        firstFriendship.setStatus("PENDING");

        Friendship secondFriendship = Friendship.fromDTO(friendshipDTO, user2, user1);
        secondFriendship.setStatus("RECEIVED");

        List<Friendship> friendships = new ArrayList<>();
        friendships.add(friendshipRepository.save(firstFriendship));
        friendships.add(friendshipRepository.save(secondFriendship));

        return friendships;
    }

    @Override
    public List<Friendship> updateFriendshipStatus(Long friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId).orElse(null);
        assert friendship != null;
        Long firstUserId = friendship.getFirstUser().getId();
        Long secondUserId = friendship.getSecondUser().getId();
        Friendship firstFriendship = friendshipRepository.findFriendshipByFirstUserIdAndSecondUserId(firstUserId, secondUserId);
        Friendship secondFriendship = friendshipRepository.findFriendshipByFirstUserIdAndSecondUserId(secondUserId, firstUserId);
        firstFriendship.setStatus("ACCEPTED");
        secondFriendship.setStatus("ACCEPTED");
        friendshipRepository.save(firstFriendship);
        friendshipRepository.save(secondFriendship);

        List<Friendship> friendships = new ArrayList<>();
        friendships.add(firstFriendship);
        friendships.add(secondFriendship);
        return friendships;
    }

    @Override
    public void deleteFriendshipById(Long friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId).orElse(null);
        assert friendship != null;

        Long firstUserId = friendship.getFirstUser().getId();
        Long secondUserId = friendship.getSecondUser().getId();
        Friendship firstFriendship = friendshipRepository.findFriendshipByFirstUserIdAndSecondUserId(firstUserId, secondUserId);
        Friendship secondFriendship = friendshipRepository.findFriendshipByFirstUserIdAndSecondUserId(secondUserId, firstUserId);

        friendshipRepository.deleteById(firstFriendship.getId());
        friendshipRepository.deleteById(secondFriendship.getId());
    }

    @Override
    public Friendship findFriendshipByFirstUserIdAndSecondUserId(Long firstUserId, Long secondUserId) {
        return friendshipRepository.findFriendshipByFirstUserIdAndSecondUserId(firstUserId, secondUserId);
    }
}
