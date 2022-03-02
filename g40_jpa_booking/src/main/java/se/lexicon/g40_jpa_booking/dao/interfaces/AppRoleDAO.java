package se.lexicon.g40_jpa_booking.dao.interfaces;

import se.lexicon.g40_jpa_booking.model.constants.UserRole;
import se.lexicon.g40_jpa_booking.model.entity.AppRole;

import java.util.Optional;

public interface AppRoleDAO extends DAOGenericCRUD<AppRole, String> {
    Optional<AppRole> findByUserRole(UserRole userRole);
}
