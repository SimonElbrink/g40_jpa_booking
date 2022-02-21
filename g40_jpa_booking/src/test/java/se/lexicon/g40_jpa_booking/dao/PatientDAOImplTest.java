package se.lexicon.g40_jpa_booking.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.g40_jpa_booking.model.Patient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PatientDAOImplTest {

    @Autowired
    PatientDAOImpl testObject;

    @BeforeEach
    void setUp() {

    }

    @Test
    void save() {
        Patient patient = new Patient(null,
                "199001011234",
                "Olga", "Olsson",
                LocalDate.parse("1990-01-01"));

        Patient result = testObject.save(patient);
        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    void findById() {



    }
}