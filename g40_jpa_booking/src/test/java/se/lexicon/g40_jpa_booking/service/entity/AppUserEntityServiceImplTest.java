package se.lexicon.g40_jpa_booking.service.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g40_jpa_booking.model.constants.UserRole;
import se.lexicon.g40_jpa_booking.model.dto.form.AppUserForm;
import se.lexicon.g40_jpa_booking.model.entity.AppRole;
import se.lexicon.g40_jpa_booking.model.entity.AppUser;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
class AppUserEntityServiceImplTest {

    @Autowired
    AppUserEntityServiceImpl testObject;
    @Autowired
    TestEntityManager em;

    @BeforeEach
    void setUp() {

        Stream.of(UserRole.values())
                .forEach(userRole -> em.persist(new AppRole(userRole.toString())));
    }

    @Test
    void create() {
        AppUserForm appUserForm = new AppUserForm();
        appUserForm.setUsername("susanne.cederholm");
        appUserForm.setPassword("It's a secret");

        AppUser result = testObject.create(appUserForm);
        em.flush();

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("susanne.cederholm", result.getUsername());
        assertEquals("It's a secret", result.getPassword());
    }

    @Test
    void update() {
    }
}