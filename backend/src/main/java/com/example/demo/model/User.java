package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String username;

    @Column(name = "contraseña")
    private String password;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellido")
    private String lastname;

    @Column(name = "edad")
    private Integer age;

    @Column(name = "genero")
    private String gender;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "imagen_perfil")
    private String profilePicture;

    @Column(name = "fecha_registro")
    @CreationTimestamp
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
