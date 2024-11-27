package com.example.demo.services.impl;

import com.example.demo.model.Publication;
import com.example.demo.model.User;
import com.example.demo.services.PublicationService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@Transactional
class PublicationServiceImplTest {

    @Autowired
    PublicationService publicationService;

    @Test
    void findAllPublications() {
        final int SIZE_EXPECTED = 13;

        List<Publication> publications = publicationService.findAllPublications();
        final int SIZE_ACTUAL = publications.size();

        assertEquals(SIZE_EXPECTED, SIZE_ACTUAL);
    }

    @Test
    void findUserPublications() {
        final String TITLE_EXPECTED = "Tutorial de Java";

        final long USER_ID = 4;
        List<Publication> publication = publicationService.findUserPublications(USER_ID);

        final String TITLE_ACTUAL = publication.get(0).getTitle();

        assertEquals(TITLE_EXPECTED, TITLE_ACTUAL);
    }

    @Test
    void createPublication() {
        String TITLE = "New Post";
        String DESCRIPTION = "Some description...";
        String CATEGORY = "Some category";
        User user = new User();
        user.setId(1L);

        Publication publication = new Publication(TITLE, DESCRIPTION, CATEGORY, null, user);

        Publication newPub = publicationService.createPublication(publication);

        assertEquals(TITLE, newPub.getTitle());
        assertEquals(DESCRIPTION, newPub.getDescription());
        assertEquals(CATEGORY, newPub.getCategory());
        assertEquals(user.getId(), newPub.getUser().getId());
    }

    @Test
    void findPublicationById() {
        final String TITLE_EXPECTED = "Lanzamiento de nuevo producto";

        final long PUB_ID = 4;
        Publication publication = publicationService.findPublicationById(PUB_ID);

        final String TITLE_ACTUAL = publication.getTitle();
        assertEquals(TITLE_EXPECTED, TITLE_ACTUAL);
    }

    @Test
    void updatePublication() {
        String TITLE = "New Post";
        String DESCRIPTION = "Some description...";
        User USER = new User();
        USER.setId(2L);

        String TITLE_UPDATED = "New Post";
        String DESCRIPTION_UPDATED = "Some description...";
        User USER_UPDATED = new User();
        USER_UPDATED.setId(3L);

        Publication newPublication = new Publication(TITLE,  DESCRIPTION, null, null, USER);
        log.info("", newPublication);
        publicationService.createPublication(newPublication);

        newPublication.setTitle(TITLE_UPDATED);
        newPublication.setDescription(DESCRIPTION_UPDATED);
        newPublication.setUser(USER_UPDATED);

        Publication updatedPublication = publicationService.updatePublication(newPublication);
        log.info("", updatedPublication);

        assertEquals(TITLE_UPDATED, updatedPublication.getTitle());
        assertEquals(DESCRIPTION_UPDATED, updatedPublication.getDescription());
        assertEquals(USER_UPDATED.getId(), updatedPublication.getUser().getId());
    }

    @Test
    void deletePublicationById() {
    }
}