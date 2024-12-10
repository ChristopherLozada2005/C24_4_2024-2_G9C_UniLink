package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;
    private String title;
    private String description;
    private String category;
    private String hasImage;
    private String imageUrl;
    private Long userId;
    private LocalDateTime pubDate;
    private String userName;
    private int commentCount;
    private UserDTO user;
    private User usersito;

}
