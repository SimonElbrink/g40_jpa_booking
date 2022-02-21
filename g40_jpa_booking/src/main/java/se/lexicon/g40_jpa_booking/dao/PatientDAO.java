package se.lexicon.g40_jpa_booking.dao;

import se.lexicon.g40_jpa_booking.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientDAO {

    Patient save(Patient patient);
    Optional<Patient> findById(String id);
    List<Patient> findAll();
    Optional<Patient> findByPnr(String pnr);
    List<Patient> findByName(String name);

    void delete(String id);

}
