package se.lexicon.g40_jpa_booking.dao.interfaces;


import se.lexicon.g40_jpa_booking.model.entity.ContactInfo;

import java.util.Optional;

public interface ContactInfoDAO extends DAOGenericCRUD<ContactInfo, String>{
    Optional<ContactInfo> findByEmail(String email);
}
