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

    @JoinColumn(name = "comentario_id_parent")
    @ManyToOne
    Comment replyTo;


    public static CommentDTO toDto(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setCommDate(comment.getCommDate());
        dto.setText(comment.getText());

        UserDTO user = new UserDTO();
        user.setId(comment.getUser().getId());
        user.setName(comment.getUser().getName());
        user.setHasImage(comment.getUser().getHasImage());

        dto.setUser(user);
        return dto;
    }

}
