package se.lexicon.g40_jpa_booking.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g40_jpa_booking.dao.interfaces.AppRoleDAO;
import se.lexicon.g40_jpa_booking.dao.interfaces.AppUserDAO;
import se.lexicon.g40_jpa_booking.exception.AppResourceNotFoundException;
import se.lexicon.g40_jpa_booking.model.constants.UserRole;
import se.lexicon.g40_jpa_booking.model.dto.form.AppUserForm;
import se.lexicon.g40_jpa_booking.model.entity.AppRole;
import se.lexicon.g40_jpa_booking.model.entity.AppUser;

import java.util.List;

@Service
public class AppUserEntityServiceImpl implements AppUserEntityService{

    private final AppUserDAO appUserDAO;
    private final AppRoleDAO appRoleDAO;

    @Autowired
    public AppUserEntityServiceImpl(AppUserDAO appUserDAO, AppRoleDAO appRoleDAO) {
        this.appUserDAO = appUserDAO;
        this.appRoleDAO = appRoleDAO;
    }

    @Override
    public AppUser findByUserName(String userName) {
        return appUserDAO.findByUsername(userName)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find the user with provided userName"));
    }

    @Override
    public List<AppUser> findByUserRole(UserRole userRole) {
        return appUserDAO.findByUserRole(userRole);
    }

    @Override
    public AppUser create(AppUserForm appUserForm) {
        if (appUserForm == null){
            throw new IllegalArgumentException("appUserForm was null");
        }
        AppUser appUser = new AppUser();
        appUser.setUsername(appUserForm.getUsername());
        appUser.setPassword(appUserForm.getPassword());

        AppRole role  = appRoleDAO.findByUserRole(UserRole.ROLE_PATIENT_USER)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find AppRole"));

        appUser.addRole(role);

        return appUserDAO.save(appUser);
    }

    @Override
    public AppUser findById(String id) {
        return appUserDAO.findById(id)
                .orElseThrow( () -> new AppResourceNotFoundException("Could not find AppUser with this id"));
    }

    @Override
    public List<AppUser> findAll() {
        return appUserDAO.findAll();
    }

    @Override
    public AppUser update(String id, AppUserForm appUserForm) {
        AppUser appUser = findById(id);

        appUser.setUsername(appUserForm.getUsername());
        appUser.setPassword(appUserForm.getPassword());

        appUser = appUserDAO.save(appUser);

        return appUser;
    }

    @Override
    public void delete(String id) {
        appUserDAO.delete(id);
    }
}
