package se.lexicon.g40_jpa_booking.dao.interfaces;

import se.lexicon.g40_jpa_booking.model.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientDAO extends DAOGenericCRUD<Patient, String>{

    Optional<Patient> findByPnr(String pnr);
    List<Patient> findByName(String name);

}
