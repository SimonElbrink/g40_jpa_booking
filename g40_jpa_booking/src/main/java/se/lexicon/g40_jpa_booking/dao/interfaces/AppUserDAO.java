package se.lexicon.g40_jpa_booking.dao.interfaces;

import se.lexicon.g40_jpa_booking.model.constants.UserRole;
import se.lexicon.g40_jpa_booking.model.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserDAO extends DAOGenericCRUD<AppUser, String>{
    Optional<AppUser> findByUsername(String username);
    List<AppUser> findByUserRole(UserRole role);
}
