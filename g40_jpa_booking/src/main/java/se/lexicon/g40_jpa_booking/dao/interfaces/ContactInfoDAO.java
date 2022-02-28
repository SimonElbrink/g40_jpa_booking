package se.lexicon.g40_jpa_booking.dao.interfaces;


import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.g40_jpa_booking.model.entity.ContactInfo;

import java.util.Optional;

public interface ContactInfoDAO extends JpaRepository<ContactInfo, String> {
    Optional<ContactInfo> findByEmailIgnoreCase(String email);
}
