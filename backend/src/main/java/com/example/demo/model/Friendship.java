package com.example.demo.model;

import com.example.demo.dto.FriendshipDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDateTime;

@Entity
@Table(name = "amistad")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estado", columnDefinition = "VARCHAR(255) DEFAULT 'PENDING'")
    private String status;

    @JoinColumn(name = "primer_usuario")
    @ManyToOne
    private User firstUser;

    @JoinColumn(name = "segundo_usuario")
    @ManyToOne
    private User secondUser;

    @Column(name = "fecha_amistad")
    @CreationTimestamp
    private LocalDateTime friendshipDate;

    public static Friendship fromDTO(FriendshipDTO dto, User user1, User user2) {
        Friendship friendship = new Friendship();
        friendship.setFirstUser(user1);
        friendship.setSecondUser(user2);
        return friendship;
    }

    public static FriendshipDTO toDTO(Friendship friendship) {
        FriendshipDTO dto = new FriendshipDTO();
        dto.setId(friendship.getId());
        dto.setStatus(friendship.getStatus());

        User firstUser = friendship.getFirstUser();
        User secondUser = friendship.getSecondUser();

        dto.setFirstUserId(firstUser.getId());
        dto.setSecondUserId(secondUser.getId());
        dto.setSecondUserName(secondUser.getName());
        dto.setSecondUserLastname(secondUser.getLastname());
        dto.setSecondUserUsername(secondUser.getUsername());
        dto.setSecondUserHasImage(secondUser.getHasImage());
        return dto;
    }

}
