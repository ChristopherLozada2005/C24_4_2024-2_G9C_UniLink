package com.example.demo.web;

import com.example.demo.dto.FriendshipDTO;
import com.example.demo.model.Friendship;
import com.example.demo.services.FriendshipService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FriendshipController {

    private final FriendshipService friendshipService;

    @Autowired
    public FriendshipController(FriendshipService friendshipService, UserService userService) {
        this.friendshipService = friendshipService;
    }

    @GetMapping("/friendship/status/{localUser}/{status}")
    public List<FriendshipDTO> findByFirstUserIdAndStatus(@PathVariable Long localUser, @PathVariable String status) {
        List<Friendship> friendships = friendshipService.findFriendsByFirstUserIdAndStatus(localUser, status);
        return friendships.stream()
                .map(Friendship::toDTO)
                .toList();
    }

    @GetMapping("/friendship/localUser/{firstUserId}")
    public List<Friendship> findFriendshipByFirstUserId(@PathVariable Long firstUserId) {
        return friendshipService.findFriendshipByFirstUserId(firstUserId);
    }

    @GetMapping("/friendship/{firstUserId}/{secondUserId}")
    public Friendship findFriendshipByFirstUserIdAndSecondUserId(@PathVariable Long firstUserId, @PathVariable Long secondUserId) {
        return friendshipService.findFriendshipByFirstUserIdAndSecondUserId(firstUserId, secondUserId);
    }

    @PostMapping("/friendship")
    public List<Friendship> createFriendship(@RequestBody FriendshipDTO friendshipDTO) {
        return friendshipService.createFriendshipRequest(friendshipDTO);
    }

    @PutMapping("/friendship/status/{friendshipId}")
    public List<Friendship> updateFriendshipStatus(@PathVariable Long friendshipId) {
        return friendshipService.updateFriendshipStatus(friendshipId);
    }

    @DeleteMapping("/friendship/{friendshipId}")
    public String deleteFriendshipById(@PathVariable Long friendshipId) {
        friendshipService.deleteFriendshipById(friendshipId);
        return "Successful deleted";
    }

}
