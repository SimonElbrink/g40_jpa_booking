package se.lexicon.g40_jpa_booking.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g40_jpa_booking.dao.interfaces.AppRoleDAO;
import se.lexicon.g40_jpa_booking.model.constants.UserRole;
import se.lexicon.g40_jpa_booking.model.entity.AppRole;

import javax.annotation.PostConstruct;

@Component
public class DatabaseMaintenance {

    private final AppRoleDAO appRoleDAO;

    @Autowired
    public DatabaseMaintenance(AppRoleDAO appRoleDAO) {
        this.appRoleDAO = appRoleDAO;
    }

    @PostConstruct
    @Transactional
    public void postConstruction(){
        if (appRoleDAO.findAll().isEmpty()){
            for (UserRole userRole: UserRole.values()) {
                appRoleDAO.save(new AppRole(userRole.toString()));
            }
        }
    }



}
