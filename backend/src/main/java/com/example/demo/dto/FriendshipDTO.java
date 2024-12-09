package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipDTO {

    private Long id;
    private Long firstUserId;

    private Long secondUserId;
    private String secondUserUsername;
    private String secondUserName;
    private String secondUserLastname;
    private String secondUserHasImage;

    private String status;
}
