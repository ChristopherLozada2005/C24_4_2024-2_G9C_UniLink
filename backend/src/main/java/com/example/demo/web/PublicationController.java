package com.example.demo.web;

import com.example.demo.dto.PublicationDTO;
import com.example.demo.model.Publication;
import com.example.demo.model.User;
import com.example.demo.services.PublicationService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private UserService userService;

    @GetMapping("/posts")
    public List<PublicationDTO> findAllPublications() {
        List<Publication> publications = publicationService.findAllPublications();
        return publications.stream()
                .map(Publication::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/posts/user/{user_id}")
    public List<PublicationDTO> findUserPublications(@PathVariable long user_id) {
        List<Publication> publications = publicationService.findUserPublications(user_id);
        return publications.stream()
                .map(Publication::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/posts/username/{user_name}")
    public List<PublicationDTO> findUserPublicationsByUserName(@PathVariable String user_name) {
        User user = userService.findUserByUsername(user_name);
        List<Publication> publications = publicationService.findUserPublications(user.getId());
        return publications.stream()
                .map(Publication::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/posts")
    public Publication createPublication(@RequestBody PublicationDTO publicationDTO) {
        User user = userService.findUserById(publicationDTO.getUserId());
        Publication publication = Publication.fromDto(publicationDTO, user);
        return publicationService.createPublication(publication);
    }

    @GetMapping("/posts/{pub_id}")
    public Publication findPublicationById(@PathVariable long pub_id) {
        return publicationService.findPublicationById(pub_id);
    }

    @PutMapping("/posts/{pub_id}")
    public Publication updatePublication(@PathVariable long pub_id, @RequestBody PublicationDTO publicationDTO) {
        Publication publication = publicationService.findPublicationById(pub_id);
        publication.setTitle(publicationDTO.getTitle());
        publication.setCategory(publicationDTO.getCategory());
        publication.setDescription(publicationDTO.getDescription());
        return publicationService.updatePublication(publication);
    }

    @DeleteMapping("/posts/{pub_id}")
    public void deletePublication(@PathVariable long pub_id) {
        publicationService.deletePublicationById(pub_id);
    }

}
