package se.lexicon.g40_jpa_booking.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.g40_jpa_booking.model.constants.UserRole;
import se.lexicon.g40_jpa_booking.model.entity.AppRole;

import java.util.Optional;

public interface AppRoleDAO extends JpaRepository<AppRole, String> {

    @Query("SELECT r FROM AppRole r WHERE r.Role = :role")
    Optional<AppRole> findByRole(@Param("role") UserRole userRole);
}
