package se.lexicon.g40_jpa_booking.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.g40_jpa_booking.dao.interfaces.AppUserDAO;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final AppUserDAO appUserDAO;

    @Autowired
    public UniqueUsernameValidator(AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        return !appUserDAO.findByUsername(value).isPresent();
    }
}
