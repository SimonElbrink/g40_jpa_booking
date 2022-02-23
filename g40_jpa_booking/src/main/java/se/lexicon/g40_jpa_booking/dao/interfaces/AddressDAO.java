package se.lexicon.g40_jpa_booking.dao.interfaces;

import se.lexicon.g40_jpa_booking.model.entity.Address;

import java.util.Optional;

public interface AddressDAO extends DAOGenericCRUD<Address, String> {
    Optional<Address> findByStreetZipCodeAndCity(String street, String zip, String city);
}
