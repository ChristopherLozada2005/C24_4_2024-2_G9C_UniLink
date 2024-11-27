package com.example.demo.model;

import com.example.demo.dto.PostDTO;
import com.example.demo.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "publicaciones")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String title;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "categoria")
    private String category;

    @Column(name = "fecha_publicacion")
    @CreationTimestamp
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pubDate;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference("user-publications")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonManagedReference("publication-comments")
    private List<Comment> comments;

    public Post(String title, String description, String category, LocalDateTime pubDate, User user) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.pubDate = pubDate;
        this.user = user;
    }

    public static Post fromDto(PostDTO postDTO, User user) {
        Post pub = new Post();
        pub.setTitle(postDTO.getTitle());
        pub.setCategory(postDTO.getCategory());
        pub.setDescription(postDTO.getDescription());
        pub.setUser(user);
        return pub;
    }

    public static PostDTO toDto(Post post) {
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setCategory(post.getCategory());
        dto.setPubDate(post.getPubDate());

        UserDTO userDTO = new UserDTO();
        userDTO.setId(post.getUser().getId());
        userDTO.setName(post.getUser().getName());
        dto.setUser(userDTO);

        return dto;
    }

}
