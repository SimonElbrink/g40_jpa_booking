package se.lexicon.g40_jpa_booking.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g40_jpa_booking.model.Patient;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@Transactional
class PatientDAOImplTest {

    @Autowired
    PatientDAOImpl testObject;

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
    void findById() {
        //Arrange
        String id = persistedPatients.get(1).getId();
        System.out.println(id);

        //Act
        Optional<Patient> foundPatient = testObject.findById(id);

        //Assert
        assertTrue(foundPatient.isPresent());

        Patient patient = foundPatient.get();

        assertEquals("Fredrik", patient.getFirstName());
        assertEquals("Olsson", patient.getLastName());
        assertEquals("196001014321", patient.getPnr());
        assertEquals(LocalDate.parse("1960-01-01"), patient.getBirthdate());

    }

    @Test
    void findByPnr() {
        String pnr = "1960-01-01-4321";
        Optional<Patient> patient = testObject.findByPnr(pnr);
        assertTrue(patient.isPresent());
    }

    @Test
    void findAll() {
        int expected = 3;
        List<Patient> patientsFound = testObject.findAll();
        assertEquals(expected, patientsFound.size());
    }


    @Test
    void findByName() {
        String name = "Anna";
        int expected = 2;

        List<Patient> patientList = testObject.findByName(name);

        assertEquals(expected, patientList.size());
    }

    @Test
    void delete() {
        String id = persistedPatients.get(2).getId();

        testObject.delete(id);

        assertNull(entityManager.find(Patient.class, id));
    }
}










