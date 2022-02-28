package se.lexicon.g40_jpa_booking.dao.interfaces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.g40_jpa_booking.model.entity.Premises;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class PremisesDAOTest {

    public static final String NAME = "Norr";
    @Autowired
    private PremisesDAO testObject;
    @Autowired
    private TestEntityManager em;

    public List<Premises> premises(){
        return Arrays.asList(
                new Premises(null, NAME),
                new Premises(null, "Söder"),
                new Premises(null, "Väster"),
                new Premises(null, "Väster")
        );
    }

    private Premises premises;

    @BeforeEach
    void setUp() {
        List<Premises> persistedPremises = premises().stream()
                .map(em::persist)
                .collect(Collectors.toList());
        premises = persistedPremises.get(0);
    }

    @Test
    void findByName() {

        List<Premises> result = testObject.findAllByName("Väster");
        int expected = 2;

        assertEquals(expected, result.size());
    }
}