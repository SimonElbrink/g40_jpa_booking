package se.lexicon.g40_jpa_booking.service.entity;


import se.lexicon.g40_jpa_booking.model.dto.form.BookingForm;
import se.lexicon.g40_jpa_booking.model.dto.form.PremisesForm;
import se.lexicon.g40_jpa_booking.model.entity.Premises;

public interface PremisesEntityService extends GenericEntityService<Premises, String, PremisesForm>{
    Premises addNewBooking(String premisesId, BookingForm bookingForm);
    Premises removeBooking(String premisesId, String bookingId);
    Premises reallocateBooking(String premisesId, String bookingId);
}
