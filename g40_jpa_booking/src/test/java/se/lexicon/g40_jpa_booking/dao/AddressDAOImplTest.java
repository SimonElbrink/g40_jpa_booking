package se.lexicon.g40_jpa_booking.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g40_jpa_booking.model.Address;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
class AddressDAOImplTest {

    public static final String STREET_ADDRESS = "Storgatan 1";
    public static final String ZIP_CODE = "35236";
    public static final String CITY = "Växjö";
    @Autowired
    private AddressDAOImpl testObject;
    @Autowired
    private TestEntityManager em;

    public List<Address> addresses(){
        return Arrays.asList(
                new Address(null, STREET_ADDRESS, ZIP_CODE, CITY),
                new Address(null, "Storgatan 2", "35236", "Växjö"),
                new Address(null, "Storgatan 1", "33137", "Värnamo")
        );
    }

    private Address address;

    @BeforeEach
    void setUp() {
        List<Address> addresses = addresses().stream()
                .map(em::persist)
                .collect(Collectors.toList());
        address = addresses.get(0);
    }

    @Test
    void findByStreetZipCodeAndCity() {
        Optional<Address> result = testObject.findByStreetZipCodeAndCity(STREET_ADDRESS, ZIP_CODE, CITY);
        assertTrue(result.isPresent());
    }

    @Test
    void save_persist() {
        Address address = new Address(null, "Hjalmar Petris väg 32", "12345", "Växjö");
        Address result = testObject.save(address);
        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    void findById() {
        assertTrue(testObject.findById(address.getId()).isPresent());
    }

    @Test
    void findAll() {
        assertEquals(3, testObject.findAll().size());
    }

    @Test
    void delete() {
        testObject.delete(address.getId());
        assertNull(em.find(Address.class, address.getId()));
    }
}