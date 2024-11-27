package com.example.demo.model;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "comentarios")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "texto")
    private String text;

    @Column(name = "fecha_comentario")
    @CreationTimestamp
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime commDate;

    @JoinColumn(name = "usuario_id")
    @ManyToOne
    @JsonBackReference
    User user;

    @JoinColumn(name = "publicacion_id")
    @ManyToOne
    @JsonBackReference
    Post post;

    @JoinColumn(name = "comentario_id")
    @ManyToOne
    Comment comment;


    public static CommentDTO toDto(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setText(comment.getText());

        UserDTO user = new UserDTO();
        user.setName(comment.getUser().getName());
        user.setProfilePicture(comment.getUser().getProfilePicture());

        dto.setUser(user);
        return dto;
    }

}
