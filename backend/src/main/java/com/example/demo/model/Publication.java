package com.example.demo.model;

import com.example.demo.dto.PublicationDTO;
import com.example.demo.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
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
public class Publication {

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

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    @JsonManagedReference("publication-comments")
    private List<Comment> comments;

    public Publication(String title, String description, String category, LocalDateTime pubDate, User user) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.pubDate = pubDate;
        this.user = user;
    }

    public static Publication fromDto(PublicationDTO publicationDTO, User user) {
        Publication pub = new Publication();
        pub.setTitle(publicationDTO.getTitle());
        pub.setCategory(publicationDTO.getCategory());
        pub.setDescription(publicationDTO.getDescription());
        pub.setUser(user);
        return pub;
    }

    public static PublicationDTO toDto(Publication publication) {
        PublicationDTO dto = new PublicationDTO();
        dto.setId(publication.getId());
        dto.setTitle(publication.getTitle());
        dto.setDescription(publication.getDescription());
        dto.setCategory(publication.getCategory());
        dto.setPubDate(publication.getPubDate());

        UserDTO userDTO = new UserDTO();
        userDTO.setId(publication.getUser().getId());
        userDTO.setName(publication.getUser().getName());
        dto.setUser(userDTO);

        return dto;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", pubDate=" + pubDate +
                ", user=" + user +
                ", comments=" + comments +
                '}';
    }
}
