package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Long id;
    private String text;
    private Long postId;
    private Long userId;
    private Long replyToId;
    private LocalDateTime commDate;
    private UserDTO user;

}
