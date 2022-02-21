package se.lexicon.g40_jpa_booking.dao;


import se.lexicon.g40_jpa_booking.model.ContactInfo;

import java.util.Optional;

public interface ContactInfoDAO extends DAOGenericCRUD<ContactInfo, String>{
    Optional<ContactInfo> findByEmail(String email);
}
