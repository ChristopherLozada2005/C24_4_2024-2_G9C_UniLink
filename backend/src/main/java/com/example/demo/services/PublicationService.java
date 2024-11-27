package com.example.demo.services;

import com.example.demo.model.Publication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PublicationService {

    List<Publication> findAllPublications();
    List<Publication> findUserPublications(Long id);
    Publication createPublication(Publication publication);
    Publication findPublicationById(Long id);
    Publication updatePublication(Publication publication);
    void deletePublicationById(Long id);


}
