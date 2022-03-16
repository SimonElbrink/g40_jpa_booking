package se.lexicon.g40_jpa_booking.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g40_jpa_booking.dao.interfaces.BookingDAO;
import se.lexicon.g40_jpa_booking.dao.interfaces.PatientDAO;
import se.lexicon.g40_jpa_booking.exception.AppResourceNotFoundException;
import se.lexicon.g40_jpa_booking.model.dto.form.PatientForm;
import se.lexicon.g40_jpa_booking.model.entity.Booking;
import se.lexicon.g40_jpa_booking.model.entity.Patient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PatientEntityServiceImpl implements PatientEntityService{

    private final PatientDAO patientDAO;
    private final AppUserEntityService appUserEntityService;
    private final ContactInfoEntityService contactInfoEntityService;
    private final BookingDAO bookingDAO;

    @Autowired
    public PatientEntityServiceImpl(PatientDAO patientDAO, AppUserEntityService appUserEntityService, ContactInfoEntityService contactInfoEntityService, BookingDAO bookingDAO) {
        this.patientDAO = patientDAO;
        this.appUserEntityService = appUserEntityService;
        this.contactInfoEntityService = contactInfoEntityService;
        this.bookingDAO = bookingDAO;
    }

    @Override
    public Patient create(PatientForm patientForm) {

        if (patientForm == null) throw new IllegalArgumentException("Patientform is null");

        Patient patient = new Patient();
        patient.setFirstName(patientForm.getFirstName());
        patient.setLastName(patientForm.getLastName());
        patient.setPnr(patientForm.getPnr());
        patient.setBirthdate(LocalDate.parse(patientForm.getBirthDate()));
        patient.setContactInfo(
                contactInfoEntityService.create(patientForm.getContactInfo())
        );
        patient.setAppUser(
                appUserEntityService.create(patientForm.getUserCredentials())
        );

        return patientDAO.save(patient);
    }

    @Override
    public Patient findById(String id) {
        return patientDAO.findById(id)
                .orElseThrow(() -> new AppResourceNotFoundException("Could Not Find patient with Provided ID"));
    }

    @Override
    public List<Patient> findAll() {
        return patientDAO.findAll();
    }

    @Override
    public Patient update(String id, PatientForm patientForm) {

        Patient patient = findById(id);
        Optional<Patient> optional = patientDAO.findByPnr(patientForm.getPnr());

        if (optional.isPresent() && !optional.get().getId().equals(id)){
            throw new IllegalArgumentException("Pnr is already used!");
        }

        patient.setFirstName(patientForm.getFirstName());
        patient.setLastName(patientForm.getLastName());
        patient.setPnr(patientForm.getPnr());
        patient.setBirthdate(LocalDate.parse(patientForm.getBirthDate()));

        return patientDAO.save(patient);
    }

    @Override
    public void delete(String id) {
        patientDAO.delete(id);
    }

    @Override
    public Patient findByPnr(String pnr) {
        return patientDAO.findByPnr(pnr)
                .orElseThrow(() -> new AppResourceNotFoundException("Could not find patient with Provided pnr"));
    }

    @Override
    public List<Patient> findByName(String name) {
        return patientDAO.findByName(name);
    }

    @Override
    public Patient addBooking(String id, String bookingId) {
        Patient patient = findById(id);
        Booking booking = bookingDAO.findById(bookingId)
                .orElseThrow(()-> new AppResourceNotFoundException("Booking with id was not found."));

        patient.addBooking(booking);

        patient = patientDAO.save(patient);
        return patient;
    }

    @Override
    public Patient removeBooking(String id, String bookingId) {
        Patient patient = findById(id);
        Booking booking = bookingDAO.findById(bookingId)
                .orElseThrow(()-> new AppResourceNotFoundException("Booking with id was not found."));

        patient.removeBooking(booking);

        patient = patientDAO.save(patient);
        return patient;
    }
}
