package se.lexicon.g40_jpa_booking.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.g40_jpa_booking.model.constants.UserRole;
import se.lexicon.g40_jpa_booking.model.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserDAO extends JpaRepository<AppUser, String> {
    Optional<AppUser> findByUsernameIgnoreCase(String username);
    @Query("SELECT u FROM AppUser u JOIN FETCH u.roles as role WHERE role = :role")
    List<AppUser> findAllByRole(@Param("role") UserRole role);
}
