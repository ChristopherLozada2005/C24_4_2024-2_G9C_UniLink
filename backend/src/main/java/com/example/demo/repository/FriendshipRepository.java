package com.example.demo.repository;

import com.example.demo.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    List<Friendship> findFriendshipByFirstUserId(Long firstUserId);
    List<Friendship> findFriendshipByFirstUserIdAndStatus(Long firstUserId, String status);
    Friendship findFriendshipByFirstUserIdAndSecondUserId(Long firsUserId, Long secondUserId);

}
