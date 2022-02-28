package se.lexicon.g40_jpa_booking.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.g40_jpa_booking.model.entity.Address;

import java.util.Optional;

public interface AddressDAO extends JpaRepository<Address, String> {
    @Query("SELECT a FROM Address a WHERE UPPER(a.streetAddress) = UPPER(:street) AND UPPER(a.zipCode) = UPPER(:zipcode) AND UPPER(a.city) = UPPER(:city)")
    Optional<Address> findByStreetZipCodeAndCity(@Param("street")String street, @Param("zipcode") String zip, @Param("city") String city);

//    Optional<Address> findAddressByStreetAddressIgnoreCaseAndZipCodeIgnoreCaseAndCityIgnoreCase(String street, String zip, String city);

}
