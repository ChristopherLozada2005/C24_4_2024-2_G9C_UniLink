package com.example.demo.services.impl;

import com.example.demo.model.Publication;
import com.example.demo.repository.PublicationRepository;
import com.example.demo.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    PublicationRepository publicationRepository;

    @Override
    public List<Publication> findAllPublications() {
        return publicationRepository.findAllByOrderByPubDateDesc();
    }

    @Override
    public List<Publication> findUserPublications(Long user_id) {
        return publicationRepository.findByUserIdOrderByPubDateDesc(user_id);
    }

    @Override
    public Publication createPublication(Publication publication) {
        return publicationRepository.save(publication);
    }

    @Override
    public Publication findPublicationById(Long id) {
        return publicationRepository.findById(id).orElse(null);
    }

    @Override
    public Publication updatePublication(Publication publication) {
        return publicationRepository.save(publication);
    }

    @Override
    public void deletePublicationById(Long id) {
        try {
            publicationRepository.deleteById(id);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
