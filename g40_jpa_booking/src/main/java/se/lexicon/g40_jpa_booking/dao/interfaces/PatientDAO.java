package se.lexicon.g40_jpa_booking.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.g40_jpa_booking.model.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientDAO extends JpaRepository<Patient, String> {
    @Query("SELECT p FROM Patient p WHERE p.pnr = :pnr")
    Optional<Patient> findByPnr(@Param("pnr") String pnr);

    @Query("SELECT p FROM Patient p WHERE LOWER(CONCAT(p.firstName, ' ', p.lastName)) LIKE LOWER(CONCAT('%', :name, '%')) ")
    List<Patient> findByName(@Param("name") String name);




}
