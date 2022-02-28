package se.lexicon.g40_jpa_booking.dao.interfaces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.g40_jpa_booking.model.entity.Patient;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
 @DataJpaTest
class PatientDAOTest {

    @Autowired
    PatientDAO testObject;

    @Autowired
    TestEntityManager entityManager;

    public List<Patient> patientList(){
        return Arrays.asList(
                new Patient(null,"196501011243", "Anna", "Olsson", LocalDate.parse("1965-01-01")),
                new Patient(null,"196001014321", "Fredrik", "Olsson", LocalDate.parse("1960-01-01")),
                new Patient(null,"199001015678", "Anna", "Alfredsson", LocalDate.parse("1990-01-01"))
        );
    }
    private List<Patient> persistedPatients;

    @BeforeEach
    void setUp() {
        persistedPatients = patientList().stream()
                .map(entityManager::persist)
                .collect(Collectors.toList());
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
    void findByPnr() {
        String pnr = "196001014321";
        Optional<Patient> patient = testObject.findByPnr(pnr);
        assertTrue(patient.isPresent());
    }

    @Test
    void findByName() {
        String name = "Anna";
        int expected = 2;

        List<Patient> patientList = testObject.findByName(name);

        assertEquals(expected, patientList.size());
    }
}










