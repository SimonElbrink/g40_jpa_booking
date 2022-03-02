package se.lexicon.g40_jpa_booking.service.entity;

import se.lexicon.g40_jpa_booking.model.dto.form.PatientForm;
import se.lexicon.g40_jpa_booking.model.entity.Patient;

import java.util.List;

public interface PatientEntityService extends GenericEntityService<Patient,String, PatientForm>{

    Patient findByPnr(String pnr);
    List<Patient> findByName(String name);
    Patient addBooking(String id, String bookingId);
    Patient removeBooking(String id, String bookingId);



}
