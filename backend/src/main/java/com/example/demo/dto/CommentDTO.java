package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    Long id;
    String text;
    Long postId;
    Long userId;
    Long commendId;
    UserDTO user;
}
