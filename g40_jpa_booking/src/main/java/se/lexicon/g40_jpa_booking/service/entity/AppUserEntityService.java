package se.lexicon.g40_jpa_booking.service.entity;

import se.lexicon.g40_jpa_booking.model.constants.UserRole;
import se.lexicon.g40_jpa_booking.model.dto.form.AppUserForm;
import se.lexicon.g40_jpa_booking.model.entity.AppUser;

import java.util.List;

public interface AppUserEntityService extends GenericEntityService<AppUser, String, AppUserForm> {

    AppUser findByUserName(String userName);
    List<AppUser> findByUserRole(UserRole userRole);

}
