package se.lexicon.g40_jpa_booking.dao.interfaces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.g40_jpa_booking.model.entity.ContactInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ContactInfoDAOTest {

    public static final String EMAIL = "nisse@gmail.com";
    public static final String PHONE = "0701234567";
    @Autowired
    private ContactInfoDAO testObject;
    @Autowired
    private TestEntityManager em;

    public List<ContactInfo> contactInfos(){
        return Arrays.asList(
                new ContactInfo(null, EMAIL, PHONE),
                new ContactInfo(null, "olle@gmail.com", "0704536325"),
                new ContactInfo(null, "anna@gmail.com", "0702853578")
        );
    }

    private ContactInfo contactInfo;

    @BeforeEach
    void setUp() {
        List<ContactInfo> contactInfos = contactInfos().stream()
                .map(em::persist)
                .collect(Collectors.toList());
        contactInfo = contactInfos.get(0);
    }

    @Test
    void findByEmail() {
        String email = EMAIL.toUpperCase();
        assertTrue(testObject.findByEmailIgnoreCase(email).isPresent());
    }
}