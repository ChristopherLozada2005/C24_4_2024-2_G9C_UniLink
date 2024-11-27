package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    String text;
    Long publicationId;
    Long userId;
    Long commendId;
    UserDTO user;
}
