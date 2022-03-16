package se.lexicon.g40_jpa_booking.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.g40_jpa_booking.model.constants.UserRole;
import se.lexicon.g40_jpa_booking.model.entity.AppRole;

import java.util.Optional;

public interface AppRoleDAO extends JpaRepository<AppRole, String> {
    Optional<AppRole> findByRole(String userRole);
}
